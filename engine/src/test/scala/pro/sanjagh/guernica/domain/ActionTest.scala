package pro.sanjagh.guernica.domain

import pro.sanjagh.guernica.UnitSpec

class ActionTest extends UnitSpec {

  import Action._

  "NegativeDegreeException" should {
    "has title equal to 'Degree could not be negative.'" in {
      NegativeDegreeException.title should be("Degree could not be negative.")
    }

    "has subtype equal to 'NegativeDegreeException/Action'" in {
      NegativeDegreeException.subtype should be(Some("NegativeDegreeException/Action"))
    }

    "has description equal to None" in {
      NegativeDegreeException.description should be(None)
    }

    "has type equal to 'sanjagh://guernica/InvalidArgument/NegativeDegreeException/Action'" in {
      NegativeDegreeException.`type` should be(
        "sanjagh://guernica/InvalidArgument/NegativeDegreeException/Action"
      )
    }
  }

  "NegativeWidthException" should {
    "has title equal to 'Width could not be negative.'" in {
      NegativeWidthException.title should be("Width could not be negative.")
    }

    "has subtype equal to 'NegativeWidthException/Action'" in {
      NegativeWidthException.subtype should be(Some("NegativeWidthException/Action"))
    }

    "has description equal to None" in {
      NegativeWidthException.description should be(None)
    }

    "has type equal to 'sanjagh://guernica/InvalidArgument/NegativeWidthException/Action'" in {
      NegativeWidthException.`type` should be(
        "sanjagh://guernica/InvalidArgument/NegativeWidthException/Action"
      )
    }
  }

  "NegativeHeightException" should {
    "has title equal to 'Height could not be negative.'" in {
      NegativeHeightException.title should be("Height could not be negative.")
    }

    "has subtype equal to 'NegativeHeightException/Action'" in {
      NegativeHeightException.subtype should be(Some("NegativeHeightException/Action"))
    }

    "has description equal to None" in {
      NegativeHeightException.description should be(None)
    }

    "has type equal to 'sanjagh://guernica/InvalidArgument/NegativeHeightException/Action'" in {
      NegativeHeightException.`type` should be(
        "sanjagh://guernica/InvalidArgument/NegativeHeightException/Action"
      )
    }
  }

  "ActionFactory degree method" should {
    "returns Left(NegativeDegreeException) with negative value" in {
      degree(-1) should be(Left(NegativeDegreeException))
    }

    "returns Right(Degree) with Zero value" in {
      degree(0) should be(Right(Degree(0)))
      degree(0).map(degree => degree.value) should be(Right(0))
    }

    "returns Right(Degree) with Positive value" in {
      degree(487) should be(Right(Degree(487)))
      degree(2433).map(degree => degree.value) should be(Right(2433))
    }
  }

  "ActionFactory rect method" should {
    "returns Left(NegativeWidthException) with negative width" in {
      rect(-2, 34) should be(Left(NegativeWidthException))
    }

    "returns Left(NegativeHeightException) with negative height" in {
      rect(63, -34) should be(Left(NegativeHeightException))
    }

    "returns Right(Rect) with zero width and zero height" in {
      rect(0, 0) should be(Right(Rect(0, 0)))
      rect(0, 0).map(rotate => (rotate.width, rotate.height)) should be(Right(0.0, 0.0))
    }

    "returns Right(Rect) with positive width and height" in {
      rect(35, 56) should be(Right(Rect(35, 56)))
      rect(43, 33).map(rotate => (rotate.width, rotate.height)) should be(Right(43, 33))
    }
  }

  "ActionFactory rotate method" should {
    "returns Left(NegativeDegreeException) with negative value" in {
      rotate(-47) should be(Left(NegativeDegreeException))
    }

    "returns Right(Rotate(Degree)) with zero value" in {
      rotate(0) should be(Right(Rotate(Degree(0))))
      rotate(0).map(rotate => rotate.degree) should be(Right(Degree(0)))
    }

    "returns Right(Rotate(Degree)) with positive value" in {
      rotate(54) should be(Right(Rotate(Degree(54))))
      rotate(64).map(rotate => rotate.degree) should be(Right(Degree(64)))
    }
  }

  "ActionFactory resize method" should {
    "returns Left(NegativeWidthException) with negative width" in {
      resize(-1, 5) should be(Left(NegativeWidthException))
    }

    "returns Left(NegativeHeightException) with negative height" in {
      resize(84, -3) should be(Left(NegativeHeightException))
    }

    "returns Right(Resize(Rect)) with zero width and zero height" in {
      resize(0, 0) should be(Right(Resize(Rect(0, 0))))
      resize(0, 0).map(r => r.rect) should be(Right(Rect(0, 0)))
    }

    "returns Right(Resize(Rect)) with positive width and height" in {
      resize(12, 11) should be(Right(Resize(Rect(12, 11))))
      resize(333, 444).map(r => r.rect) should be(Right(Rect(333, 444)))
    }
  }

  "ActionFactory crop method" should {
    "returns Left(NegativeWidthException) with negative width" in {
      crop(-1, 5) should be(Left(NegativeWidthException))
    }

    "returns Left(NegativeHeightException) with negative height" in {
      crop(84, -3) should be(Left(NegativeHeightException))
    }

    "returns Right(Crop(Rect)) with zero width and zero height" in {
      crop(0, 0) should be(Right(Crop(Rect(0, 0))))
      crop(0, 0).map(r => r.rect) should be(Right(Rect(0, 0)))
    }

    "returns Right(Crop(Rect)) with positive width and height" in {
      crop(12, 11) should be(Right(Crop(Rect(12, 11))))
      crop(333, 444).map(r => r.rect) should be(Right(Rect(333, 444)))
    }
  }

  "ActionFactory cropResize method" should {
    "returns Left(NegativeWidthException) with negative width" in {
      cropResize(-1, 5) should be(Left(NegativeWidthException))
    }

    "returns Left(NegativeHeightException) with negative height" in {
      cropResize(84, -3) should be(Left(NegativeHeightException))
    }

    "returns Right(CropResize(Rect)) with zero width and zero height" in {
      cropResize(0, 0) should be(Right(CropResize(Rect(0, 0))))
      cropResize(0, 0).map(r => r.resizeRect) should be(Right(Rect(0, 0)))
    }

    "returns Right(CropResize(Rect)) with positive width and height" in {
      cropResize(12, 11) should be(Right(CropResize(Rect(12, 11))))
      cropResize(333, 444).map(r => r.resizeRect) should be(Right(Rect(333, 444)))
    }
  }
}
