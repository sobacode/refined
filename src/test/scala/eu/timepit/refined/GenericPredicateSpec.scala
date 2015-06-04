package eu.timepit.refined

import eu.timepit.refined.generic._
import org.scalacheck.Prop._
import org.scalacheck.Properties

class GenericPredicateSpec extends Properties("GenericPredicate") {

  property("Equal[_].isValid") = secure {
    Predicate[Equal[W.`1.4`.T], Double].isValid(1.4)
  }

  property("Equal[_].notValid") = secure {
    Predicate[Equal[W.`1.4`.T], Double].notValid(2.4)
  }

  property("Equal[_].show") = secure {
    Predicate[Equal[W.`1.4`.T], Double].show(0.4) ?= "(0.4 == 1.4)"
  }

  property("Equal[Symbol].isValid") = secure {
    refine[Equal[W.`'foo`.T]]('foo).isRight &&
      refine[Equal[W.`'foo`.T]]('bar).isLeft
  }

  property("IsNull.isValid") = secure {
    val p = Predicate[IsNull, String]
    p.isValid(null) && p.notValid("")
  }

  property("NonNull.show") = secure {
    Predicate[NonNull, String].show("abc") ?= "!(abc == null)"
  }
}
