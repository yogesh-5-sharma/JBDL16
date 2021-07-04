import org.example.calculator.Calculator;
import org.example.calculator.Calculator2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyTest {

    @Mock
    Calculator2 calculator2;

    @InjectMocks
    Calculator calculator;

    @BeforeClass
    public static void mainInitializer() {
        System.out.println("In main initializer");
    }

    @Before
    public void initialize() {
        System.out.println("In initializer");
    }

    @Test
    public void test1() {
        Assert.assertEquals(5, 2+3);

        when(calculator2.getString()).thenReturn("mock string");

        int result = calculator.sum(3,7);

        Assert.assertEquals(result, 10);
    }

    @Test
    public void test2() {
        Assert.assertEquals(2+2, 4);
    }
}
