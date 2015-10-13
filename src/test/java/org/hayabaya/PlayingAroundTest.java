//package org.hayabaya;
//
//import org.junit.Test;
//
//import static junit.framework.TestCase.assertEquals;
//
///**A test class to do REPL-like testing of simple Java statements.
// * Created by ktakagaki on 15/05/22.
// */
//public class PlayingAroundTest extends MyTestAbstract {
//
//    final static int[] testArray = new int[5];
//
//    @Test
//    public void finalArrayMutability(){
//
//        assertEquals("not initialized to zero", 0, testArray[0]);
//        testArray[0] = 100;
//        assertEquals("didn't update correctly", 100, testArray[0]);
//        //So static final array elements can still be changed
//
//    }
//
//}