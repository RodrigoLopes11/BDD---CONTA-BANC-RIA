import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertEquals;

/**
 * Classe que representa uma conta bancária.
 * 
 * Esta classe contém métodos para depositar, sacar e obter o saldo de uma conta
 * bancária. Além disso, inclui métodos específicos para cenários de clientes
 * especiais com saldos negativos.
 * 
 * @author Rodrigo Lopes
 * @version 1.0
 * @since Release 1.0
 */
public class bancaryConta {
    private int saldo;

    /**
     * Construtor da classe bancaryConta.
     * 
     * @param saldoInicial O saldo inicial da conta.
     */
    public bancaryConta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    /**
     * Realiza um depósito na conta.
     * 
     * @param valor O valor a ser depositado.
     */
    public void depositar(int valor) {
        saldo += valor;
    }

    /**
     * Realiza um saque na conta.
     * 
     * @param valor O valor a ser sacado.
     */
    public void sacar(int valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    /**
     * Obtém o saldo atual da conta.
     * 
     * @return O saldo atual da conta.
     */
    public int getSaldo() {
        return saldo;
    }

    private bancaryConta conta;

    /**
     * Inicializa uma conta especial com saldo negativo.
     * 
     * @param saldoInicial O saldo inicial da conta especial.
     */
    @Given("^Um cliente especial com saldo atual de -(\\d+) reais$")
    public void um_cliente_especial_com_saldo_atual_de_reais(int saldoInicial) {
        conta = new bancaryConta(-saldoInicial);
    }

    /**
     * O cliente realiza um depósito na conta.
     * 
     * @param valorDeposito O valor a ser depositado na conta.
     */
    @When("^O cliente deposita (\\d+) reais na conta$")
    public void o_cliente_deposita_reais_na_conta(int valorDeposito) {
        conta.depositar(valorDeposito);
    }

    /**
     * O cliente realiza um saque na conta.
     * 
     * @param valorSaque O valor a ser sacado da conta.
     */
    @When("^O cliente saca (\\d+) reais da conta$")
    public void o_cliente_saca_reais_da_conta(int valorSaque) {
        conta.sacar(valorSaque);
    }

    /**
     * Verifica se o saldo atual da conta é o esperado.
     * 
     * @param saldoEsperado O saldo que se espera que a conta tenha.
     */
    @Then("^O saldo atual da conta é -(\\d+) reais$")
    public void o_saldo_atual_da_conta_e_reais(int saldoEsperado) {
        assertEquals(-saldoEsperado, conta.getSaldo());
    }
}
