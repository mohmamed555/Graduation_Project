package com.example.graduationproject.Activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.graduationproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText email_register, pass_register, repass, user_name;
    Button btn_register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        email_register = findViewById(R.id.enter_email_register);
        pass_register = findViewById(R.id.enter_pass_register);
        repass = findViewById(R.id.enter_repass);
        btn_register = findViewById(R.id.to_login);
        user_name = findViewById(R.id.enter_userName);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


    }

    private void register() {

        String email = email_register.getText().toString();
        String password = pass_register.getText().toString();
        String repassword = repass.getText().toString();

        if (!email.equals("") && password.equals(repassword)) {

            if (pass_register.length() < 6) {
                pass_register.setError("كلمة السرر يجب أن تكون أكبر من 6 مدخلات");
                repass.setError("كلمة السرر يجب أن تكون أكبر من 6 مدخلات");
                pass_register.setHintTextColor(Color.RED);
                repass.setHintTextColor(Color.RED);

            }else {

                actionOfLogin(email,password);
            }

        } else {

            pass_register.setError("من فضلك راجع كلمة السرر");
            repass.setError("كلمة المرور غير متطابقه");
            pass_register.setHintTextColor(Color.RED);
            email_register.setHintTextColor(Color.RED);
            repass.setHintTextColor(Color.RED);


        }



    }

    private void actionOfLogin(String email , String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "تمت عملية التسجيل بنجاح و عليك تفعيل البريد الألكتروني الخاص بك", Toast.LENGTH_LONG).show();


                        } else {
                            email_register.setError("من فضلك أعد كتابة كلمة السرر بطريقه صحيحه");
                            Toast.makeText(getApplicationContext(), "هذا البريد الألكتروني غير صحيح", Toast.LENGTH_LONG).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "Connection Field", Toast.LENGTH_SHORT).show();
            }
        });

    }
}