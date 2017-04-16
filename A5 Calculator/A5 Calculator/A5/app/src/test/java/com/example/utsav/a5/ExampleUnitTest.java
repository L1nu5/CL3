package com.example.utsav.a5;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    private MainActivity mactivity;
    @Before
    public void setup(){
        mactivity = new MainActivity();

    }
    @Test
    public void addition_isCorrect() throws Exception {
        ArrayList<String> addn = new ArrayList<String>();
        addn.add("2");
        addn.add("+");
        addn.add("2");
        assertEquals(4, mactivity.add(addn), 0);
    }
    @Test
    public void sub_isCorrect() throws Exception {
        ArrayList<String> addn = new ArrayList<String>();
        addn.add("7");
        addn.add("-");
        addn.add("2");
        assertEquals(5, mactivity.sub(addn), 0);
    }
    @Test
    public void mul_isCorrect() throws Exception {
        ArrayList<String> addn = new ArrayList<String>();
        addn.add("2");
        addn.add("*");
        addn.add("2");
        assertEquals(4, mactivity.mul(addn), 0);
    }
    @Test
    public void nooperation_iscorrect() throws Exception {
        assertEquals(1, 1 );
    }
    @Test
    public void sine_iscorrect() throws Exception{
        ArrayList<String> sine = new ArrayList<String>();
        sine.add("1");
        sine.add("SIN");
        assertEquals(0.84 , Double.parseDouble(mactivity.sinFunct(sine)), 0);
    }
    @Test
    public void cos_iscorrect() throws Exception{
        ArrayList<String> cos = new ArrayList<String>();
        cos.add("1");
        cos.add("COS");
        assertEquals(0.54 , Double.parseDouble(mactivity.cosfn(cos)), 0);
    }
    @Test
    public void tan_iscorrect() throws Exception{
        ArrayList<String> tan = new ArrayList<String>();
        tan.add("1");
        tan.add("TAN");
        assertEquals(1.56 , Double.parseDouble(mactivity.tanfn(tan)), 0);
    }

}