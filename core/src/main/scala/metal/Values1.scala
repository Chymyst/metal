package metal

trait Values1[V1] extends Pointable {

  /** Returns the value of the object pointed by the pointer. */
  def ptrValue1[@specialized W1](ptr: VPtr[Tag]): W1

}

trait Updatable1[V1] extends Values1[V1] {

  def ptrUpdate1[@specialized W1](ptr: VPtr[Tag], v: W1): Unit

}