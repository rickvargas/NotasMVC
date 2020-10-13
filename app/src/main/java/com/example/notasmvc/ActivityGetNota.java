package com.example.notasmvc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notasmvc.controller.NotaController;
import com.example.notasmvc.model.Nota;

public class ActivityGetNota extends AppCompatActivity {
	NotaController notaController;
	EditText etTitulo, etTexto, etId;
	Button btSalvar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exibe_nota);
		
		notaController = new NotaController(getApplicationContext());
		
		etId = findViewById(R.id.editTextId);
		etTitulo = findViewById(R.id.editTextTitulo);
		etTexto = findViewById(R.id.editTextTexto);
		btSalvar = findViewById(R.id.buttonNota);
		
		etId.setEnabled(false);
		etTitulo.setEnabled(false);
		etTexto.setEnabled(false);
		btSalvar.setText("Voltar");
		
		btSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				voltar(v);
			}
		});
		
		Intent intent = getIntent();
		etId.setText(intent.getStringExtra("id_nota"));
		etTitulo.setText(intent.getStringExtra("titulo_nota"));
		etTexto.setText(intent.getStringExtra("texto_nota"));
	}
	
	public void voltar(View v){
		finish();
	}
}