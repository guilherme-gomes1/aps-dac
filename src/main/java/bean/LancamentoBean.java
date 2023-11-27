package bean;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.LancamentoDao;
import entidades.Lancamento;

@ManagedBean
public class LancamentoBean {
	private Lancamento lancamento = new Lancamento();
	private List<Lancamento> lista = LancamentoDao.acharTodos();
	
	public String salvar() {
		lista.add(lancamento);
		LancamentoDao.salvar(lancamento);
		lancamento = new Lancamento();
		return "listagem";
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public List<Lancamento> getLista() {
		return this.lista;
	}

	public void setLista(List<Lancamento> list) {
		this.lista = list;
	}
	
	public void excluirItem(Lancamento lanc) {
		lista.remove(lanc);
		LancamentoDao.remover(lanc);
	}
	
	public void mostrarItem(Lancamento lanc) {
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item " + lanc.getId(), "Descrição: " + lanc.getDescricao() + " | Tipo: " + lanc.getTipo() + " | Valor: " + lanc.getValor()));
	}
	
	public void totalReceita() {
		double receitas = 0, despesas = 0;
		for(int i=0; i < LancamentoDao.acharTodos().size(); i++) {
			if(lista.get(i).getTipo().equals("receita")) {
				receitas += lista.get(i).getValor();
			} else {
				despesas += lista.get(i).getValor();
			}
		}
		
		double totalLancamentos = receitas + despesas;
		double totalResto = receitas - despesas;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Total: ", "Receita: R$ " + receitas + " | Despesas: R$ " + despesas + " | Total de Lançamentos: R$ " + totalLancamentos + " | Valor que resta: R$ " + totalResto));
	}
}
