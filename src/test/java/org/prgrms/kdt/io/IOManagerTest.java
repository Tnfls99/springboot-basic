package org.prgrms.kdt.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.prgrms.kdt.exceptions.AmountException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IOManagerTest {

    private IOManager ioManager;


    @DisplayName("amount는 정수만 입력받는다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-100", "30", "100", "1234", "5678"})
    void getAmountInputTest(String input) {
        // given
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        IOManager ioManager = new IOManager(new Console());

        //when
        int amount = ioManager.getAmountInput();

        //then
        assertEquals(Integer.parseInt(input), amount);
    }

    @DisplayName("amount는 0보다 큰 정수가 아닌 값을 받을 경우 예외를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "1234p", "asdf", "five"})
    void getInvalidAmountInputTest(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        IOManager ioManager = new IOManager(new Console());

        assertThrows(AmountException.class, ioManager::getAmountInput);
    }
}