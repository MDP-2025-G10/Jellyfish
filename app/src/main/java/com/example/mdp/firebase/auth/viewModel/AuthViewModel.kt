package com.example.mdp.firebase.auth.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdp.firebase.auth.repository.AuthRepository
import com.example.mdp.firebase.firestore.model.User
import com.example.mdp.firebase.firestore.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch


class AuthViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> = _currentUser

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        Log.d("AuthViewModel", "Auth state changed: ${firebaseAuth.currentUser?.email}")
        _currentUser.postValue(firebaseAuth.currentUser) // Update LiveData when auth state changes
    }

    init {
        // Update current user on initialization
        _currentUser.value = authRepository.getCurrentUser()
        authRepository.addAuthStateListener(authStateListener)
    }

    fun register(email: String, password: String) {
        authRepository.register(email, password) { user ->
            viewModelScope.launch {
                user?.let {
                    val newUser = User(
                        uid = it.uid,
                        name = it.displayName ?: "",
                        email = it.email ?: "",
                        profilePic = it.photoUrl?.toString() ?: ""
                    )
                    userRepository.createUserIfNotExists(newUser)
                }
                _currentUser.postValue(user)
            }
        }
    }

    fun login(email: String, password: String) {
        authRepository.login(email, password) { user ->
            viewModelScope.launch {
                user?.let {
                    val newUser = User(
                        uid = it.uid,
                        name = it.displayName ?: "",
                        email = it.email ?: "",
                        profilePic = it.photoUrl?.toString() ?: ""
                    )
                    userRepository.createUserIfNotExists(newUser)
                }
                _currentUser.postValue(user)
            }
        }
    }

    fun signInWithGoogle() {
        viewModelScope.launch {
            val isSignedIn = authRepository.signInWithGoogle()
            val user = if (isSignedIn) authRepository.getCurrentUser() else null
            _currentUser.postValue(user)
        }
    }

    fun logout() {
        authRepository.logout()
        _currentUser.value = null
        Log.d("currentUser", "${_currentUser.value} from AuthViewModel logout")
    }

    override fun onCleared() {
        super.onCleared()
        authRepository.removeAuthStateListener(authStateListener) // Cleanup listener to prevent memory leaks
    }
}
