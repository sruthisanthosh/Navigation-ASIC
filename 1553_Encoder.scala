package encoder

import chisel3._

class Encoder extends Module{
val io = IO ( new Bundle{
val Start= Input(Bool())
val Busy= Input(Bool())
val data=Input(UInt(8.W))
val out=Output(UInt(2.W))
)}

when(~Busy)
{
RegInit shifter= 0.U(16.W)
Mux(data===1,io.out===10.b, io.out===01.b)
Shifter := io.out
Shifter <<2
}
}

