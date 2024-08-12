package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import dao.JogoDAO;
import dao.TimeDAO;
import entities.Jogo;
import entities.Time;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private String seletorTime1;
	private String seletorTime2;
	private String seletorTimeFiltro;
	private Integer golsTime1AuxEdicao;
	private Integer golsTime2AuxEdicao;
	private List<Jogo> listaDeJogos;
	private List<Jogo> listaDeJogosFiltrado = new ArrayList<Jogo>();

	public void onRowEdit(RowEditEvent<Jogo> event) {
		event.getObject().setGolsTime1(golsTime1AuxEdicao);
		event.getObject().setGolsTime2(golsTime2AuxEdicao);
		editar(event.getObject());

		FacesMessage msg = new FacesMessage("Jogo Editado", String.valueOf("ID: " + event.getObject().getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent<Jogo> event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada", String.valueOf("ID: " + event.getObject().getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
// Tentativa utilizando Hibernate Query Language
//	public String filtrar() {
//		//Encontrar o id do time selecionado
//		Integer idDoTimeFiltrado = 0;
//		List<Time> listaDeTimes = TimeDAO.buscarTodos();
//		for(Time t : listaDeTimes) {
//			if(t.getNomeTime().equalsIgnoreCase(seletorTimeFiltro)) {
//				idDoTimeFiltrado = t.getId();
//			}
//		}
//		
//		listaDeJogosFiltrado = JogoDAO.buscarComFiltro(idDoTimeFiltrado);
//		return null;
//	}

	public String filtrar() {
		List<Jogo> jogos = getListaDeJogos();
		for(Jogo j : jogos) {
			if(j.getTime1().getNomeTime().equalsIgnoreCase(seletorTimeFiltro) || 
					j.getTime2().getNomeTime().equalsIgnoreCase(seletorTimeFiltro)) {
				listaDeJogosFiltrado.add(j);
			}
		}		return null;
	}

	public String salvar() {

		// Atribui os variaveis tipo time com o time que conresponde a seu nome
		if (vericarTimes()) {
			jogo.setDataCadastro(new Date());
			
			//Atribuindo ao objeto jogo os times selecionados no cadastro
			List<Time> listaDeTimes = TimeDAO.buscarTodos();
			for (Time t : listaDeTimes) {
				if (t.getNomeTime().equalsIgnoreCase(seletorTime1)) {
					jogo.setTime1(t);
				}
				if (t.getNomeTime().equalsIgnoreCase(seletorTime2)) {
					jogo.setTime2(t);
				}
			}

			// Salvando o jogo no banco
			JogoDAO.salvar(jogo);

			// Atualizar valores dos times com os resultados da partida
			jogo.getTime1().calcularInformacoes();
			jogo.getTime2().calcularInformacoes();

			// Salvando do bd as atualizações nos times
			TimeDAO.atualizar(jogo.getTime1());
			TimeDAO.atualizar(jogo.getTime2());

			// Limpando os campos do formulario
			jogo = new Jogo();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Salvo com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Selecione Times Diferentes!"));
		}

		return null;
	}

	public String excluir(Jogo jogo) {
		JogoDAO.remover(jogo);

		// Atualizar valores dos times com os resultados da partida
		jogo.getTime1().calcularInformacoes();
		jogo.getTime2().calcularInformacoes();

		// Salvando do bd as atualizações nos times
		TimeDAO.atualizar(jogo.getTime1());
		TimeDAO.atualizar(jogo.getTime2());
		return null;
	}

	public String editar(Jogo jogo) {
		JogoDAO.atualizar(jogo);

		// Atualizar valores dos times com os resultados da partida
		jogo.getTime1().calcularInformacoes();
		jogo.getTime2().calcularInformacoes();

		// Salvando do bd as atualizações nos times
		TimeDAO.atualizar(jogo.getTime1());
		TimeDAO.atualizar(jogo.getTime2());
		return null;
	}

	private boolean vericarTimes() {
		if (seletorTime1.equalsIgnoreCase(seletorTime2)) {
			return false;
		}
		return true;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public String getSeletorTime1() {
		return seletorTime1;
	}

	public void setSeletorTime1(String seletorTime1) {
		this.seletorTime1 = seletorTime1;
	}

	public String getSeletorTime2() {
		return seletorTime2;
	}

	public void setSeletorTime2(String seletorTime2) {
		this.seletorTime2 = seletorTime2;
	}

	public List<Jogo> getListaDeJogos() {
		listaDeJogos = JogoDAO.buscarTodos();
		return listaDeJogos;
	}

	public void setListaDeJogos(List<Jogo> listaDeJogos) {
		this.listaDeJogos = listaDeJogos;
	}

	public Integer getGolsTime1AuxEdicao() {
		return golsTime1AuxEdicao;
	}

	public void setGolsTime1AuxEdicao(Integer golsTime1AuxEdicao) {
		this.golsTime1AuxEdicao = golsTime1AuxEdicao;
	}

	public Integer getGolsTime2AuxEdicao() {
		return golsTime2AuxEdicao;
	}

	public void setGolsTime2AuxEdicao(Integer golsTime2AuxEdicao) {
		this.golsTime2AuxEdicao = golsTime2AuxEdicao;
	}

	public String getSeletorTimeFiltro() {
		return seletorTimeFiltro;
	}

	public void setSeletorTimeFiltro(String seletorTimeFiltro) {
		this.seletorTimeFiltro = seletorTimeFiltro;
	}

	public List<Jogo> getListaDeJogosFiltrado() {
		return listaDeJogosFiltrado;
	}

	public void setListaDeJogosFiltrado(List<Jogo> listaDeJogosFiltrado) {
		this.listaDeJogosFiltrado = listaDeJogosFiltrado;
	}

}
