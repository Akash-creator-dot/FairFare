package com.example.fairfare

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fairfare.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        //initialize firebase
        auth=FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
      binding.textLogin.setOnClickListener {
          Handler().postDelayed({
              val intent = Intent(this, LoginActivity::class.java)
              startActivity(intent)
              overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
          }, 1000)
          finish()
      }
        binding.btnSignup.setOnClickListener {
            val name=binding.editTextName.text.toString()
            val email=binding.editTextEmail.text.toString()
            val pass=binding.editTextPassword.text.toString()
            val conpass=binding.editTextConfirmPassword.text.toString()
            if(name.isEmpty() || email.isEmpty() || pass.isEmpty() || conpass.isEmpty()){
                Toast.makeText(this, "Please Fill all the fields", Toast.LENGTH_SHORT).show()
            }else if(pass!=conpass){
                Toast.makeText(this, "Passwords Mismatch !!", Toast.LENGTH_SHORT).show()
            }else if(pass.length<6){
                Toast.makeText(this, "Please make a Strong pssword", Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(this) {
                            task->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,LoginActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "Registration Failed : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener(this){
                            task->
                        Toast.makeText(this, "Error!! ", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
