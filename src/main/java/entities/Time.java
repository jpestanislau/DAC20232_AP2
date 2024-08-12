package entities;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import dao.JogoDAO;

@Entity
public class Time {
	@Id
	@GeneratedValue
	private Integer id;
	private String nomeTime;
	private Integer pontuacao;
	private Integer numVitorias;
	private Integer numDerrotas;
	private Integer numEmpates;
	private Integer golsMarcados;
	private Integer golsSofridos;
	private Integer saldosGols;
	
	public void calcularInformacoes() {
		calcularPotuacaoVitoriasEmpatesDerrotas();
		calcularGols();
	}
	
	private void calcularPotuacaoVitoriasEmpatesDerrotas(){
		pontuacao = 0;
		numVitorias = 0;
		numEmpates = 0;
		numDerrotas = 0;
		List<Jogo> jogos = JogoDAO.buscarTodos();
		for(Jogo j : jogos) {
			//Condição de vitoria 
			if((j.getTime1().getNomeTime().equalsIgnoreCase(nomeTime) && j.getGolsTime1()>j.getGolsTime2()) || 
					j.getTime2().getNomeTime().equalsIgnoreCase(nomeTime) && j.getGolsTime2()>j.getGolsTime1()) {
				pontuacao = pontuacao + 2;
				numVitorias++;
			}
			
			//Condição de empate
			if((j.getTime1().getNomeTime().equalsIgnoreCase(nomeTime) && j.getGolsTime1()==j.getGolsTime2()) || 
					j.getTime2().getNomeTime().equalsIgnoreCase(nomeTime) && j.getGolsTime2()==j.getGolsTime1()) {
				pontuacao = pontuacao + 1;
				numEmpates++;
			}
			
			//Condição de derrota
			if((j.getTime1().getNomeTime().equalsIgnoreCase(nomeTime) && j.getGolsTime1()<j.getGolsTime2()) || 
					j.getTime2().getNomeTime().equalsIgnoreCase(nomeTime) && j.getGolsTime2()<j.getGolsTime1()) {
				numDerrotas++;
			}

			
		}
	}
	
	private void calcularGols(){
		golsMarcados = 0;
		golsSofridos = 0;
		saldosGols = 0;
		List<Jogo> jogos = JogoDAO.buscarTodos();
		for(Jogo j : jogos) {
			//Sou o time 1?
			if(j.getTime1().getNomeTime().equalsIgnoreCase(nomeTime)) {
				golsMarcados = golsMarcados + j.getGolsTime1();
				golsSofridos = golsSofridos + j.getGolsTime2();
			}
			
			//Sou o time 2?
			if(j.getTime2().getNomeTime().equalsIgnoreCase(nomeTime)) {
				golsMarcados = golsMarcados + j.getGolsTime2();
				golsSofridos = golsSofridos + j.getGolsTime1();
			}
		}
		saldosGols = golsMarcados - golsSofridos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Integer getNumVitorias() {
		return numVitorias;
	}

	public void setNumVitorias(Integer numVitorias) {
		this.numVitorias = numVitorias;
	}

	public Integer getNumDerrotas() {
		return numDerrotas;
	}

	public void setNumDerrotas(Integer numDerrotas) {
		this.numDerrotas = numDerrotas;
	}

	public Integer getNumEmpates() {
		return numEmpates;
	}

	public void setNumEmpates(Integer numEmpates) {
		this.numEmpates = numEmpates;
	}

	public Integer getGolsMarcados() {
		return golsMarcados;
	}

	public void setGolsMarcados(Integer golsMarcados) {
		this.golsMarcados = golsMarcados;
	}

	public Integer getGolsSofridos() {
		return golsSofridos;
	}

	public void setGolsSofridos(Integer golsSofridos) {
		this.golsSofridos = golsSofridos;
	}

	public Integer getSaldosGols() {
		return saldosGols;
	}

	public void setSaldosGols(Integer saldosGols) {
		this.saldosGols = saldosGols;
	}

}
