package poo.uva.es.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import fabricante.externo.tarjetas.*;

public class TarjetaMonederoTest {
	public static final double ERROR_ADMISIBLE = 0.00001;

	@Test
	public void testInicializarSinPasarValorSaldoValido() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894");
		assertNotNull(tarjeta);
		assertEquals(tarjeta.getSaldoActual(),0,ERROR_ADMISIBLE);
	}

	@Test
	public void testInicializarConSaldoValido() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 0.1);
		assertNotNull(tarjeta);
		assertEquals(tarjeta.getSaldoActual(),0.1,ERROR_ADMISIBLE);
	}
	
	@Test
	public void testInicializarConArgumentoSaldoValidoPeroCero() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 0);
		assertNotNull(tarjeta);
		assertEquals(tarjeta.getSaldoActual(),0,ERROR_ADMISIBLE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarSinPasarValorInvalidoCredencialIncorrecta() {
		@SuppressWarnings("unused")
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo89");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarInvalidoCredencialDeDescontar() {
		@SuppressWarnings("unused")
		TarjetaMonedero tarjeta = new TarjetaMonedero("6Z1y00Nm31aA-571");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConSaldoNegativo() {
		@SuppressWarnings("unused")
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", -0.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConSaldoCorrectoPeroCredencialIncorrecta() {
		@SuppressWarnings("unused")
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894a", 0.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarConSaldoInicialCorrectoPeroCredencialDeDescontar() {
		@SuppressWarnings("unused")
		TarjetaMonedero tarjeta = new TarjetaMonedero("6Z1y00Nm31aA-571", 0.1);
	}

	@Test
	public void testRecargaSaldoValidoConSaldoInicialCero() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894");
		tarjeta.recargaSaldo("A156Bv09_1zXo894", 10.50);
		assertEquals(tarjeta.getSaldoActual(), 10.50, ERROR_ADMISIBLE);
	}
	
	@Test
	public void testRecargaSaldoValidoConSaldoInicialPositivo() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.50);
		tarjeta.recargaSaldo("A156Bv09_1zXo894", 10.50);
		assertEquals(tarjeta.getSaldoActual(), 21.00, ERROR_ADMISIBLE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRecargaSaldoInvalidoCantidadNegativa() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.50);
		tarjeta.recargaSaldo("A156Bv09_1zXo894", -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRecargaSaldoInvalidoCantidadCero() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.50);
		tarjeta.recargaSaldo("A156Bv09_1zXo894", 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRecargaSaldoCredencialIncorrecta() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.50);
		tarjeta.recargaSaldo("cualquiera", 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRecargaSaldoPeroCredencialDeDescontar() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.50);
		tarjeta.recargaSaldo("6Z1y00Nm31aA-57", 1);
	}

	@Test
	public void testDescontarDelSaldoValidoDejandoSaldo() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.50);
		tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", 10);
		assertEquals(tarjeta.getSaldoActual(), 0.50, ERROR_ADMISIBLE);
	}
	
	@Test
	public void testDescontarDelSaldoValidoDejandoCero() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.50);
		tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", 10.50);
		assertEquals(tarjeta.getSaldoActual(), 0, ERROR_ADMISIBLE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDescontarDelSaldoCantidadInvalidaCero() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 1.50);
		tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDescontarDelSaldoCantidadInvalidaNegativa() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 5.25);
		tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", -4.37);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDescontarDelSaldoCantidadInvalidaMayorQueSaldo() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 15.47);
		tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", 15.48);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDescontarDelSaldoCredencialInvalida() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 15.47);
		tarjeta.descontarDelSaldo("6Z1y00Nm31aA571", 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDescontarDelSaldoPeroCredencialDeCargar() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 15.47);
		tarjeta.descontarDelSaldo("A156Bv09_1zXo894", 1);
	}
}
