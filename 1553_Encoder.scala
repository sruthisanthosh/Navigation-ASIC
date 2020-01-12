package encoder

import chisel3._

class Encoder extends Module{
val io = IO ( new Bundle{
val StopClk   = Input(Bool())
val Start   = Input(Bool())
val Busy   = Input(Bool())


)}
}
