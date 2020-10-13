package com.example.notasmvc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notasmvc.controller.NotaController;
import com.example.notasmvc.model.Nota;

import java.util.ArrayList;

public class ActivityNotasMain extends AppCompatActivity {
	ListView listView;
	NotaController notaController;
	NotaAdapterListView notaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		notaController = new NotaController(getApplicationContext());
		listView = findViewById(R.id.listView);
		notaAdapter = new NotaAdapterListView(
				getApplicationContext(),
				R.layout.list_notas,
				new ArrayList()
		);
		listView.setAdapter(notaAdapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// retorna uma nota prq foi passado uma lista de notas como entrada
				Nota nota = (Nota) parent.getItemAtPosition(position);
				System.out.println("Click normal: nota.getIdNota(): "+ nota.getIdNota());
				exibirNota(view, nota.getIdNota());
			}
		});
		
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
				// retorna uma nota prq foi passado uma lista de notas como entrada
				final Nota nota = (Nota) parent.getItemAtPosition(position);
				System.out.println("Click longo: nota.getIdNota(): "+ nota.getIdNota());
				System.out.println("Click normal: position: "+ position);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ActivityNotasMain.this);
				builder.setTitle("Selecione a Acao: ")
						.setItems(new String[]{"Editar", "Excluir"}, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// The 'which' argument contains the index position of option
								switch(which){
									case 0:
										editaNota(view, nota.getIdNota());
										break;
									case 1:
										excluirNota(view, nota.getIdNota());
										break;
									default:
										System.out.println("Descarte...");
										break;
								}
							}
						});
				builder.create();
				builder.show();
				return true;
			}
		});
	}
	
	protected void onStart() {
		super.onStart();
		showNotas();
	}

	public void novaNota(View v){
		Intent intent = new Intent(this, ActivityAddNota.class);
		startActivity(intent);
	}
	
	// poderia passar a nota diretamente, mas bora usar o getNota...
	public void editaNota(View v, Integer idNota){
		Nota nota = notaController.getNota(idNota);
		Intent intent = new Intent(this, ActivityUpdateNota.class);

		intent.putExtra("id_nota", nota.getIdNota().toString());
		intent.putExtra("titulo_nota", nota.getTitulo());
		intent.putExtra("texto_nota", nota.getTexto());
		startActivity(intent);
	}
	
	// poderia passar a nota diretamente, mas bora usar o getNota...
	public void excluirNota(View v, Integer idNota){
		Nota nota = notaController.getNota(idNota);
		Boolean resultado = notaController.excluirNota(nota);
		if(resultado == true){
			Toast.makeText(this, "Nota excluida com sucesso!", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Erro ao deletar nota...", Toast.LENGTH_SHORT).show();
		}
		showNotas();
	}
	
	// poderia passar a nota diretamente, mas bora usar o getNota...
	public void exibirNota(View v, Integer idNota){
		Nota nota = notaController.getNota(idNota);
		
		Intent intent = new Intent(this, ActivityGetNota.class);
		intent.putExtra("id_nota", nota.getIdNota().toString());
		intent.putExtra("titulo_nota", nota.getTitulo());
		intent.putExtra("texto_nota", nota.getTexto());
		startActivity(intent);
	}
	
	public void showNotas(){
		notaAdapter.clear();
		notaAdapter.addAll(notaController.getListaNotas());
	}
	
}
