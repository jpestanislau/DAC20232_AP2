package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.TimeDAO;
import entities.Time;

@ManagedBean
public class TimeBean {
	private List<Time> listaDeTimes;
	
	public List<Time> getListaDeJogos() {
		listaDeTimes = TimeDAO.buscarTodos();
		return listaDeTimes;
	}
	public void setListaDeJogos(List<Time> listaDeTimes) {
		this.listaDeTimes = listaDeTimes;
	}
}
