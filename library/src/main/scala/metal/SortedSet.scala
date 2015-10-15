package metal

import scala.reflect.ClassTag

import spire.algebra.Order

import syntax._

trait SortedSet[K] extends MSet[K] { self =>
  implicit def orderK: Order[K]
  def copy: SortedSet[K]
}

/*
final class SortedSetImpl[@specialized(Int) K](allocatedSize: Int)(implicit val ctK: ClassTag[K], val orderK: Order[K]) extends SortedSet[K] {

  private[this] var _items: Array[K] = new Array[K](allocatedSize)
  private[this] var _size: Int = 0
  @inline final def size: Int = _size
  @inline final def isEmpty = _size == 0
  @inline final def nonEmpty = _size > 0

  def absorb(newItems: Array[K], newSize: Int): Unit = {
    _items = newItems
    _size = newSize
  }

  def copy: SortedSet[K] = {
    val res = new SortedSetImpl[K](0)(ctK, orderK)
    res.absorb(_items.clone, _size)
    res
  }

  protected def findWhere(item: K): Int = {
    var lb = 0
    var ub = _size
    while (lb < ub) {
      val m = (lb + ub) >>> 1
      val c = orderK.compare(_items(m), item)
      if (c == 0) return m
      if (c < 0)
        lb = m + 1
      else
        ub = m
    }
    // now lb == ub
    if (lb == _size) return ~_size
    val c = orderK.compare(_items(lb), item)
    if (c == 0) return lb
    if (c > 0) return ~lb
    sys.error("Should not happen")
  }

  final def ptrRemoveAndAdvance(ptr: VPtr[Tag]): Ptr[Tag] = {
    val pos = ptr.v.toInt
    java.lang.System.arraycopy(_items, pos + 1, _items, pos, _size - pos - 1)
    _size -= 1
    if (pos >= _size) Ptr.Null[Tag] else ptr
  }

  final def ptrRemove(ptr: VPtr[Tag]): Unit = ptrRemoveAndAdvance(ptr)

  @inline final def ptrFind(key: K): Ptr[Tag] = {
    val ind = findWhere(key)
    if (ind >= 0) VPtr[Tag](ind) else Ptr.Null[Tag]
  }

  @inline final def ptrAddKey(key: K): VPtr[Tag] = {
    val pos = findWhere(key)
    if (pos < 0) {
      val ipos = ~pos
      val new_items = if (_size < _items.length) _items else {
        val arr = new Array[K](_items.length * 2)
        java.lang.System.arraycopy(_items, 0, arr, 0, ipos)
        arr
      }
      java.lang.System.arraycopy(_items, ipos, new_items, ipos + 1, _size - ipos)
      _items = new_items
      _items(ipos) = key
      _size += 1
      VPtr[Tag](ipos)
    } else VPtr[Tag](pos)
  }

  @inline final def ptrStart: Ptr[Tag] = if (_size == 0) Ptr.Null[Tag] else VPtr[Tag](0)
  @inline final def ptrNext(ptr: VPtr[Tag]): Ptr[Tag] = if (ptr.v == _size - 1) Ptr.Null[Tag] else VPtr[Tag](ptr.v + 1)
  @inline final def ptrKey(ptr: VPtr[Tag]): K = _items(ptr.v.toInt)
}

object SortedSet extends MSetFactory[Any, Order] {
  def empty[K](implicit c: ClassTag[K], ord: Order[K], e: LBEv[K]): SortedSet[K] = new SortedSetImpl[K](8)
  def apply[K](items: K*)(implicit ct: ClassTag[K], ord: Order[K], e: LBEv[K]): SortedSet[K] = {
    val s = empty[K](ct, ord, e)
    items.foreach { a => s += a }
    s
  }
  def ofSize[K](n: Int)(implicit c: ClassTag[K], ord: Order[K], e: LBEv[K]): SortedSet[K] = new SortedSetImpl[K](n)
}
 */