package metal
package mutable

trait Collection extends generic.Collection {

  /** Returns an immutable version of this mutable collection. The mutable instance is cleared. */
  def result(): Immutable

  /** Removes all elements from this collection, deallocating its arrays. */
  def clear(): Unit

  /** Removes all elements from this collection, keeping the all allocated arrays. */
  def reset(): Unit

}
