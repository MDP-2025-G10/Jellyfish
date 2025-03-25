package com.example.mdp.firebase.repository

import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import com.example.mdp.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.security.SecureRandom

class AuthRepository(private val auth: FirebaseAuth, private val context: Context) {

    private val credentialManager = CredentialManager.create(context)

    fun getCurrentUser(): FirebaseUser? {
        Log.d("currentUser", "${auth.currentUser} from AuthRepository getCurrentUser")
        return auth.currentUser
    }

    fun register(email: String, password: String, onResult: (FirebaseUser?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(auth.currentUser)
                } else {
                    onResult(null)
                }
            }
    }

    fun login(email: String, password: String, onResult: (FirebaseUser?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(auth.currentUser)
                } else {
                    onResult(null)
                }
            }
    }

    suspend fun signInWithGoogle(): Boolean {
        val clientId = context.getString(R.string.web_client_id)
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false) // Show only previously signed-in accounts
            .setServerClientId(clientId) // Replace with your actual web client ID
            .setAutoSelectEnabled(true) // Auto-select if only one account is available
            .setNonce(generateNonce()) // Recommended for security
            .build()
        Log.d("AuthRepository", "Requesting credentials with GoogleIdOption: $googleIdOption")


        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return try {
            // Get the credential
            val result: GetCredentialResponse = credentialManager.getCredential(context, request)
            handleSignIn(result)
        } catch (e: GetCredentialException) {
            Log.e("AuthRepository", "Google Sign-In failed: ${e.message}")
            false
        }
    }

    fun logout() {
        auth.signOut()
        Log.d("currentUser", "${auth.currentUser} from AuthRepository logout")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = androidx.credentials.ClearCredentialStateRequest()
                credentialManager.clearCredentialState(request)
                Log.d("AuthRepository", "Google credentials cleared successfully")
            } catch (e: Exception) {
                Log.e("AuthRepository", "Failed to clear Google credentials: ${e.message}")
            }
        }
    }

    fun addAuthStateListener(listener: FirebaseAuth.AuthStateListener) {
        auth.addAuthStateListener(listener)
    }

    fun removeAuthStateListener(listener: FirebaseAuth.AuthStateListener) {
        auth.removeAuthStateListener(listener)
    }

    private suspend fun handleSignIn(result: GetCredentialResponse): Boolean {
        return when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)
                        val idToken = googleIdTokenCredential.idToken // Extract ID token

                        // Use the ID token to sign in with Firebase
                        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                        auth.signInWithCredential(firebaseCredential).await()
                        true
                    } catch (e: Exception) {
                        Log.e(
                            "AuthRepository",
                            "Failed to handle Google ID Token credential: ${e.message}"
                        )
                        false
                    }
                } else {
                    Log.e("AuthRepository", "Unexpected type of credential")
                    false
                }
            }

            else -> {
                Log.e("AuthRepository", "Unexpected type of credential")
                false
            }
        }
    }

    private fun generateNonce(): String {
        val nonce = ByteArray(16)
        SecureRandom().nextBytes(nonce)
        return Base64.encodeToString(nonce, Base64.NO_WRAP)
    }
}
