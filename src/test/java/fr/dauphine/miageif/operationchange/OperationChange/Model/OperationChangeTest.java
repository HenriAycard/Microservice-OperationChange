package fr.dauphine.miageif.operationchange.OperationChange.Model;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class OperationChangeTest {
    OperationChange op;

    @Before
    public void setUp() {
        op = new OperationChange();
    }

    @Test
    public void testGetSetID(){
        op.setId(1234L);
        assertEquals(Long.valueOf(1234L), op.getId());
    }

    @Test
    public void testGetSetSource(){
        op.setSource("USD");
        assertEquals("USD", op.getSource());
    }

    @Test
    public void testGetSetDest(){
        op.setDestination("GBP");
        assertEquals("GBP", op.getDest());
    }

    @Test
    public void testGetSetTaux(){
        op.setTaux(new BigDecimal(0.7175));
        assertEquals(new BigDecimal(0.7175), op.getTaux());
    }

    @Test
    public void testGetSetDate(){
        op.setDate("2021-06-21");
        assertEquals("2021-06-21", op.getDate());
    }

    @Test
    public void testEquals(){
        op.setId(1243L);
        op.setSource("USD");
        op.setDestination("GBP");
        op.setMontant(500);
        op.setTaux(new BigDecimal(0.7175));
        op.setDate("2021-06-21");
        op.setCounterpart("JP-AYCARD");
        OperationChange op2 = new OperationChange(1243L,"USD","GBP", 500, new BigDecimal(0.7175), "2021-06-21", "JP-AYCARD");
        assertTrue(op.equals(op2));
    }
}
