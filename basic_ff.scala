package problem

import chisel3._

class Block extends Module{
val io= IO(new Bundle{
val input=Input(0.U(4.W))
val enable=Input((Bool())
val output=Output(0.U(4.W))
})
  when(io.enable){
    io.out := io.in
  }
  .otherwise{
    io.out=0.U
  }
}
