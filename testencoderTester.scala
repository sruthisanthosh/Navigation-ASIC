class EncoderTester(c : encoder) extends PeekPokeTester(c){

for ( i <- 0 until 40){

       val a = rnd.nextInt(65536)
       val tx_valid = rnd.nextInt(2)
       val tx_csw = rnd.nextInt(2)
       val tx_dw = rnd.nextInt(2)
       poke(c.io.tx_dword,a)
       poke(c.io.tx_valid, tx_valid)
       poke(c.io.tx_csw, tx_csw)
       poke(c.io.tx_dw, tx_dw)
       step(50)

    }
}
