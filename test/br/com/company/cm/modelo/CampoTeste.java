package br.com.company.cm.modelo;

import br.com.company.cm.excecoes.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CampoTeste {

    private Campo campo;

    @BeforeEach
    void inicilizar(){
        campo = new Campo(3,3);
    }

    @Test
    void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3,2);
        boolean resultado = campo.adicionarViznho(vizinho);

        assertTrue(resultado);

    }

    @Test
    void testeVizinhoDistancia1Direita() {
        Campo vizinho = new Campo(3,4);
        boolean resultado = campo.adicionarViznho(vizinho);

        assertTrue(resultado);

    }

    @Test
    void testeVizinhoDistancia1EmCima() {
        Campo vizinho = new Campo(2,3);
        boolean resultado = campo.adicionarViznho(vizinho);

        assertTrue(resultado);

    }

    @Test
    void testeVizinhoDistancia1EmBaixo() {
        Campo vizinho = new Campo(4,3);
        boolean resultado = campo.adicionarViznho(vizinho);

        assertTrue(resultado);

    }

    @Test
    void testeVizinhoDistancia2() {
        Campo vizinho = new Campo(2,2);
        boolean resultado = campo.adicionarViznho(vizinho);

        assertTrue(resultado);

    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1,1);
        boolean resultado = campo.adicionarViznho(vizinho);

        assertFalse(resultado);

    }

    @Test
    void testeValorPadraoMarcado(){
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado(){
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado(){
        campo.minar();

        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1(){

        Campo campo11 = new Campo(1,1);
        Campo campo22 = new Campo(2,2);
        campo22.adicionarViznho(campo11);

        campo.adicionarViznho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());

    }

    @Test
    void testeAbrirComVizinhos2(){

        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1,1);
        campo12.minar();

        Campo campo22 = new Campo(2,2);
        campo22.adicionarViznho(campo11);
        campo22.adicionarViznho(campo12);

        campo.adicionarViznho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());

    }

    @Test
    void testeLinha(){
        assertEquals(3, campo.getLinha());
    }

    @Test
    void testeColuna(){
        assertEquals(3, campo.getColuna());
    }

    @Test
    void testeMinasNaVizinhanca(){
        long totalMinas = campo.minasNaVizinhanca();
        assertEquals(0, campo.minasNaVizinhanca());
    }

    @Test
    void testeObjetivoAlcancadoDesvendado(){
        campo.abrir();
        campo.alternarMarcacao();
        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoAlcancadoProtegido(){
        campo.minar();
        campo.alternarMarcacao();
        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeReiniciarJogo(){
        campo.reiniciarJogo();
        assertFalse(campo.isMinado() && campo.isMarcado() && campo.isAberto());
    }

    @Test
    void testeToString(){

    }
}
