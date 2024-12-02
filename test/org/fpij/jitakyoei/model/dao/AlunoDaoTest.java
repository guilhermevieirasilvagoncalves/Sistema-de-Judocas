package org.fpij.jitakyoei.model.dao;

import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlunoDaoTest {
	
	private static DAO<Aluno> alunoDao;
	private static Aluno aluno;
	private static Entidade entidade;
	private static DAO<Entidade> entidadeDao;
	private static Endereco endereco;
	private static Filiado f1;
	private static Filiado filiadoProf;
	private static Professor professor;
	private static DAO<Professor> professorDao;
	
	@BeforeClass
	public static void setUp(){
		DatabaseManager.setEnviroment(DatabaseManager.TEST);
		f1 = new Filiado();
		f1.setNome("Aécio");
		f1.setCpf("036.464.453-27");
		f1.setDataNascimento(new Date());
		f1.setDataCadastro(new Date());
		f1.setId(1332L);
		
		endereco = new Endereco();
		endereco.setBairro("Dirceu");
		endereco.setCep("64078-213");
		endereco.setCidade("Teresina");
		endereco.setEstado("PI");
		endereco.setRua("Rua Des. Berilo Mota");
		
		filiadoProf = new Filiado();
		filiadoProf.setNome("Professor");
		filiadoProf.setCpf("036.464.453-27");
		filiadoProf.setDataNascimento(new Date());
		filiadoProf.setDataCadastro(new Date());
		filiadoProf.setId(3332L);
		filiadoProf.setEndereco(endereco);
		
		professor = new Professor();
		professor.setFiliado(filiadoProf);
		
		entidade = new Entidade();
		entidade.setEndereco(endereco);
		entidade.setNome("Academia 1");
		entidade.setTelefone1("(086)1234-5432");
		
		aluno = new Aluno();
		aluno.setFiliado(f1);
		aluno.setProfessor(professor);
		aluno.setEntidade(entidade);
		
		alunoDao = new DAOImpl<Aluno>(Aluno.class);
		entidadeDao = new DAOImpl<Entidade>(Entidade.class);
		professorDao = new DAOImpl<Professor>(Professor.class);
	}

	public static void clearDatabase(){
		List<Aluno> a11 = alunoDao.list();
		for (Aluno each : a11) {
			alunoDao.delete(each);
		}
		assertEquals(0, alunoDao.list().size());

		List<Entidade> e11 = entidadeDao.list();
		for (Entidade each : e11) {
			entidadeDao.delete(each);
		}
		assertEquals(0, entidadeDao.list().size());

		List<Professor> p11 = professorDao.list();
		for (Professor each : p11) {
			professorDao.delete(each);
		}
		assertEquals(0, professorDao.list().size());
	}
	
	
	// @Test
	// public void  testSalvarAlunoComAssociassoes() throws Exception{
	// 	clearDatabase();
		
	// 	alunoDao.save(aluno);
	// 	assertEquals("036.464.453-27", alunoDao.get(aluno).getFiliado().getCpf());
	// 	assertEquals("Aécio", alunoDao.get(aluno).getFiliado().getNome());
	// 	assertEquals("Professor", alunoDao.get(aluno).getProfessor().getFiliado().getNome());
	// 	assertEquals("Dirceu", alunoDao.get(aluno).getProfessor().getFiliado().getEndereco().getBairro());
	// }
	
	// @Test
	// public void updateAluno() throws Exception{
	// 	clearDatabase();
	// 	assertEquals(0, alunoDao.list().size());
		
	// 	alunoDao.save(aluno);
	// 	assertEquals(1, alunoDao.list().size());
	// 	assertEquals("Aécio", aluno.getFiliado().getNome());
		
	// 	Aluno a1 = alunoDao.get(aluno);
	// 	a1.getFiliado().setNome("TesteUpdate");
	// 	alunoDao.save(a1);
		
	// 	Aluno a2 = alunoDao.get(a1);
	// 	assertEquals("TesteUpdate", a2.getFiliado().getNome());
	// 	assertEquals(1, alunoDao.list().size());
	// }

	@Test
	public void deleteEntidade() throws Exception{ // Teste ID 6

		clearDatabase();
		assertEquals(0, entidadeDao.list().size());
		
		entidadeDao.save(entidade);
		assertEquals(1, entidadeDao.list().size());

		Entidade e1 = entidadeDao.get(entidade);

		entidadeDao.delete(e1);

		assertEquals(0, entidadeDao.list().size());
	}

	@Test
	public void deleteAluno() throws Exception{ // Teste ID 5

		clearDatabase();
		assertEquals(0, alunoDao.list().size());
		
		alunoDao.save(aluno);
		assertEquals(1, alunoDao.list().size());

		Aluno a1 = alunoDao.get(aluno);

		alunoDao.delete(a1);

		assertEquals(0, alunoDao.list().size());
	}

	@Test
	public void deleteProfessor() throws Exception{ // Teste ID 8

		clearDatabase();
		assertEquals(0, professorDao.list().size());
		
		professorDao.save(professor);
		assertEquals(1, professorDao.list().size());

		Professor p1 = professorDao.get(professor);

		professorDao.delete(p1);

		assertEquals(0, professorDao.list().size());
	}
	
	// @Test
	// public void testSearchAluno() throws Exception{
	// 	clearDatabase();
	// 	alunoDao.save(aluno);
		
	// 	Filiado f = new Filiado();
	// 	f.setNome("Aécio");
	// 	Aluno a = new Aluno();
	// 	a.setFiliado(f);
		
	// 	List<Aluno> result = alunoDao.search(a);
	// 	assertEquals(1, result.size());
	// 	assertEquals("036.464.453-27", result.get(0).getFiliado().getCpf());
		
	// 	clearDatabase();
	// 	assertEquals(0, alunoDao.search(a).size());
	// }
	
	@AfterClass
	public static void closeDatabase(){
		clearDatabase();
		DatabaseManager.close();
	}
	
}
