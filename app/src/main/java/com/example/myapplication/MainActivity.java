package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    EditText kg,ans,m;
    RadioGroup sexe,niveau;
    Button btn1,btn2;
    TextView res;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      kg=findViewById(R.id.kg);
      ans=findViewById(R.id.ans);
      m=findViewById(R.id.m);
      res=findViewById(R.id.res);

      sexe=findViewById(R.id.sexe);
      niveau=findViewById(R.id.niveau);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                if(kg.getText().toString().isEmpty()){
                    Toast.makeText(this, "Poids vide", Toast.LENGTH_SHORT).show();
                    kg.requestFocus();
                    return;
                }
                if(ans.getText().toString().isEmpty()){
                    Toast.makeText(this, "Age vide", Toast.LENGTH_SHORT).show();
                    ans.requestFocus();
                    return;
                }
                if(m.getText().toString().isEmpty()){
                    Toast.makeText(this, "Taille vide", Toast.LENGTH_SHORT).show();
                    m.requestFocus();
                    return;
                }

                double poids = Double.valueOf(kg.getText().toString());
                double taille = Double.valueOf(m.getText().toString());
                int age = Integer.valueOf(ans.getText().toString());
                double resultat=0;
                double resultats=0;


                if(sexe.getCheckedRadioButtonId()==R.id.H)
                    resultat = (13.76*poids)+(500.33*taille)-(6.76*age)+66.48 ;

                else{
                    if (sexe.getCheckedRadioButtonId()==R.id.F)
                        resultat = (9.74*poids)+(185*taille)-(4.7*age)+655.1 ;
                }


                res.setText(String.format("Resultat : %.2f",resultat));


                Toast.makeText(this, "Resultat : " +resultat, Toast.LENGTH_SHORT).show();

                if(niveau.getCheckedRadioButtonId()==R.id.sed){
                    resultats = resultat*1.36;
                }

                if (niveau.getCheckedRadioButtonId() == R.id.leg){
                     resultats = resultat * 1.56;
                }

                if (niveau.getCheckedRadioButtonId() == R.id.actif){
                     resultats = resultat * 1.64;
                }
                if (niveau.getCheckedRadioButtonId() == R.id.tres){
                    resultats = resultat * 1.82;
                }

                Toast.makeText(this, "Resultat niveau : " +resultats, Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn2:
                kg.getText().clear();
                ans.getText().clear();
                m.getText().clear();
                sexe.check(R.id.H);
                niveau.check(R.id.sed);
                res.setText("Resultat : ");
                kg.requestFocus();
                break;
        }
    }
}