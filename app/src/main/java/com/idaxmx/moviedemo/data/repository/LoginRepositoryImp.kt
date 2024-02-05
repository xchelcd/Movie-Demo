package com.idaxmx.moviedemo.data.repository

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.idaxmx.moviedemo.util.network_response.Error
import com.idaxmx.moviedemo.util.network_response.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginCredentialsRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : LoginRepository {
    override suspend fun login(username: String, password: String): Resource {
        val authResult: AuthResult? = firebaseAuth.signInWithEmailAndPassword(username, password).await()
        Log.d("LoginCredentialsRepositoryImp", "authResult: $authResult")
        return if (authResult?.user != null) {
            Resource.Successful(true)
        } else {
            Resource.Error(
                error = Error(
                    errorMessage = "Failed auth"
                )
            )
        }
    }

}

//class LoginFacebookRepositoryImp @Inject constructor(
//
//): LoginRepository {
//    override suspend fun login(): Resource {
//
//    }
//
//}