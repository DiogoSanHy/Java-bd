import java.sql.Connection;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Main {

	public static void main(String[] args) {
		
		
		Connection conexao = null;
		Scanner ler = new Scanner(System.in);
		int codigo = 1;
		int op = 0;
		
		try {
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");

			} catch (ClassNotFoundException ex) {
				System.out.println("Erro ao registrar o driver do mysql");
			}
			String url = "jdbc:mysql://localhost:3306/tarefa";
			String usuario = "root";
			String senha = "root";
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexão estabelecida!");
		} catch (java.sql.SQLException sqle) {
			System.out.println("O erro foi " + sqle);
			System.out.println("Deu pau em tudo!");
		}
		do {
			System.out.println("BEM VINDO A SUA AGENDA PESSOAL!");
			System.out.println("1 - Listar tarefas cadastradas");
			System.out.println("2 - Cadastrar tarefas");
			System.out.println("3 - Excluir tarefa");
			System.out.println("4 - Alterar tarefa");
			System.out.println("5 - Sair");
			System.out.println("DIGITE A OPÇÃO DESEJADA: ");
			op = ler.nextInt();
			
			switch(op){
				case 1:
					String comandoSQL1 = "select * from tarefa;";
					try {
						PreparedStatement ps = conexao.prepareStatement(comandoSQL1);
						ResultSet result = ps.executeQuery(comandoSQL1); //recebe a consulta
						
						if (result.next()) {
							while(result.next()) {
								int id = result.getInt("idtarefa");
								String nome = result.getString("nome");
								int horaIni = result.getInt("horaIni");
								String descricao = result.getString("descricao");
								int mes = result.getInt("mes");
								int dia = result.getInt("dia");
								
								System.out.println(id + " - " + nome + " - " + horaIni + " - " + descricao + " - " + mes + " - " + dia );
							}
						} else {
							System.out.println("Não à tarefas cadastradas");
						}
					} catch (java.sql.SQLException e) {
						e.printStackTrace();
					}
					
					break;
				case 2: 
					
					System.out.println("Digite o mês da tarefa: ");
					int mes = ler.nextInt();
					
					System.out.println("Digite o dia da tarefa: ");
					int dia = ler.nextInt();
					
					System.out.println("Digite o horario da tarefa: ");
					int horaIni = ler.nextInt();
					
					System.out.println("Digite o nome da tarefa: ");
					ler.nextLine();
					String nome = ler.nextLine();
					
					System.out.println("Digite a descrcao da tarefa: ");
					String descricao = ler.nextLine();
					
					String comandoSQL2 = "insert into tarefa(nome, horaIni, descricao, mes, dia) values (?,?,?,?,?)";
					try {
						PreparedStatement ps = conexao.prepareStatement(comandoSQL2);
						ps.setString(1, nome);
						ps.setInt(2, horaIni);
						ps.setString(3, descricao);
						ps.setInt(4, mes);
						ps.setInt(5, dia);
						ps.execute();
						ps.close();
					} catch (java.sql.SQLException e) {
						e.printStackTrace();
					}
					
					break;
				case 3:
					System.out.println("Digite o código da tarefa: ");
					codigo = ler.nextInt();
					String comandoSQL3 = "SELECT idtarefa FROM tarefa WHERE idtarefa = ?";
					try {
					    PreparedStatement ps = conexao.prepareStatement(comandoSQL3);
					    ps.setInt(1, codigo);
					    ResultSet result = ps.executeQuery(); // recebe a consulta

					    if (result.next()) {
					    	comandoSQL3 = "DELETE FROM tarefa WHERE idtarefa = ?";
					    	PreparedStatement ps2 = conexao.prepareStatement(comandoSQL3);
					    	ps2.setInt(1, codigo);
					    	ps2.execute();
					    	ps2.close();
					    	
					    	System.out.println("Tarefa excluída com sucesso!");
					    } else {
					        System.out.println("ID inválido.");
					    }
					} catch (java.sql.SQLException e) {
					    e.printStackTrace();
					}
					break;
				case 4:
					System.out.println("Digite o código da tarefa: ");
					codigo = ler.nextInt();
					String comandoSQL4 = "SELECT idtarefa FROM tarefa WHERE idtarefa = ?";
					try {
					    PreparedStatement ps = conexao.prepareStatement(comandoSQL4);
					    ps.setInt(1, codigo);
					    ResultSet result = ps.executeQuery(); // recebe a consulta

					    if (result.next()) {
					    	System.out.println("Digite o mês da tarefa: ");
							int mesNv = ler.nextInt();
							
							System.out.println("Digite o dia da tarefa: ");
							int diaNv = ler.nextInt();
							
							System.out.println("Digite o horario da tarefa: ");
							int horaIniNv = ler.nextInt();
							
							System.out.println("Digite o nome da tarefa: ");
							ler.nextLine();
							String nomeNv = ler.nextLine();
							
							System.out.println("Digite a descrcao da tarefa: ");
							String descricaoNv = ler.nextLine();
							
							comandoSQL4 = "UPDATE `tarefa`.`tarefa` SET `nome` = ?, `horaIni` = ?, `descricao` = ?, `mes` = ?, `dia` = ? WHERE (`idtarefa` = ?);";
							
							PreparedStatement ps6 = conexao.prepareStatement(comandoSQL4);
						    ps6.setString(1, nomeNv);
							ps6.setInt(2, horaIniNv);
							ps6.setString(3, descricaoNv);
							ps6.setInt(4, mesNv);
							ps6.setInt(5, diaNv);
						    ps6.setInt(6, codigo);
							ps6.execute();
							ps6.close();
							
							System.out.println("Tarefa alterada com sucesso!");
					    } else {
					        System.out.println("ID inválido.");
					    }
					} catch (java.sql.SQLException e) {
					    e.printStackTrace();
					}
					break;
			}
			
		}while(op > 0 && op <=4);
		
		System.out.println("OBRIGADO POR UTILIZAR NOSSA AGENDA! VOLTE SEMPRE!");
		
		

	}
}
