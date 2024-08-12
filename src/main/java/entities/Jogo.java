package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Jogo {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Date dataPartida;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	private Set<Time> times = new HashSet<Time>();
	
	@Column(name="gols_do_time1")
	private Integer golsTime1;
	
	@Column(name="gols_do_time2")
	private Integer golsTime2;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Time time1;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Time time2;
	
	public Time[] getListaDeTimes(){
		Time[] times = {time1,time2};
		return times;
	}
	
//	public Set<Time> getTimes() {
//		return times;
//	}
//	public void setTimes(Set<Time> times) {
//		this.times = times;
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataPartida() {
		return dataPartida;
	}
	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Integer getGolsTime1() {
		return golsTime1;
	}
	public void setGolsTime1(Integer golsTime1) {
		this.golsTime1 = golsTime1;
	}
	public Integer getGolsTime2() {
		return golsTime2;
	}
	public void setGolsTime2(Integer golsTime2) {
		this.golsTime2 = golsTime2;
	}

	public Time getTime1() {
		return time1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public Time getTime2() {
		return time2;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}
	

}
