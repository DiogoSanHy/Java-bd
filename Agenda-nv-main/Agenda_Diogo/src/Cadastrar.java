
public class Cadastrar {
	private String nome;
    private String descricao;
    private int dataDia;
    private int dataMes;
    private int horario;
    private int cod;
	
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getDataDia() {
		return dataDia;
	}
	public void setData(int dataDia) {
		this.dataDia = dataDia;
	}
	public int getHorario() {
		return horario;
	}
	public void setHorario(int horario) {
		this.horario = horario;
	}
	public int getDataMes() {
		return dataMes;
	}
	public void setDataMes(int dataMes) {
		this.dataMes = dataMes;
	}
	
	
	public int getCod() {
		return cod;
	}
	
	
	public Cadastrar(String nome, String descricao, int dataMes, int dataDia, int horario,int cod) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataDia = dataDia;
		this.horario = horario;
		this.cod = cod;
		this.dataMes = dataMes;

	}
	
	public void detalhe() {
		System.out.println("Código: "+cod+"\nNome: "+nome+"\nMês: "+dataMes+"\nDia: "+dataDia+"\nHorario: "+horario+"\nDescrição: "+descricao+"\n");

	}
    
}
