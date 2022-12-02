package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate date = LocalDate.of(2030, 01, 01);
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	@Test
	public void deveRetornarTrueParaDataAtual() {
		LocalDate date = LocalDate.now();
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	@Test
	public void deveRetornarFalseParaDataPassada() {
		LocalDate date = LocalDate.of(2020, 2, 3);
		assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
}
