package util;

import dao.TimeDAO;
import entities.Time;

public class Main {

	public static void main(String[] args) {
		Time timeA = new Time();
		Time timeB = new Time();
		Time timeC = new Time();
		timeA.setNomeTime("A");
		timeB.setNomeTime("B");
		timeC.setNomeTime("C");
		
		TimeDAO.salvar(timeA);
		TimeDAO.salvar(timeB);
		TimeDAO.salvar(timeC);

	}

}
