package com.example.notasmvc.controller;

import android.content.Context;

import com.example.notasmvc.model.Nota;
import com.example.notasmvc.model.NotaDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class NotaController {
	Context mContext;
	NotaDAO notaDAO;
	
	public NotaController(Context c){
		this.mContext = c;
		// controlador é quem deve falar com o DB...
		this.notaDAO = new NotaDAO(this.mContext);
	}
	
	public Nota cadastrarNovaNota(Nota nota){
		return this.notaDAO.insereNota(nota);
	}
	
	public Boolean atualizaNota(Nota nota){
		return this.notaDAO.updateNota(nota);
	}
	
	public Boolean excluirNota(Nota nota) {
		return this.notaDAO.deleteNota(nota);
	}
	
	public Nota getNota(Integer idNota) {
		return this.notaDAO.getNota(idNota);
	}
	
	public ArrayList<Nota> getListaNotas() {
		return this.notaDAO.getListaNotas();
	}
}