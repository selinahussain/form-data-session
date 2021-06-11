package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Play.materializer
import play.api.http.Status
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{ControllerComponents, Headers}
import play.api.test._
import play.api.test.Helpers._

class FormControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf[ControllerComponents]

  object testFormController extends FormController (
    controllerComponents
  )


  "FormController GET" should {

    //name
    "render the nameForm() from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val name = controller.nameForm().apply(FakeRequest(GET, "/name"))
      status(name) mustBe OK
      contentType(name) mustBe Some("text/html")
      contentAsString(name) must include ("name")
    }
    "render the nameForm page from the application" in {
      val controller = inject[FormController]
      val name = controller.nameForm().apply(FakeRequest(GET, "/name"))
      status(name) mustBe OK
      contentType(name) mustBe Some("text/html")
      contentAsString(name) must include ("name")
    }




    //age
    "render the ageForm() from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val age = controller.ageForm().apply(FakeRequest(GET, "/age"))
      status(age) mustBe OK
      contentType(age) mustBe Some("text/html")
      contentAsString(age) must include ("age")
    }
    "render the ageForm page from the application" in {
      val controller = inject[FormController]
      val age = controller.ageForm().apply(FakeRequest(GET, "/age"))
      status(age) mustBe OK
      contentType(age) mustBe Some("text/html")
      contentAsString(age) must include ("age")
    }




    //gender
    "render the genderForm() from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val gender = controller.genderForm().apply(FakeRequest(GET, "/gender"))
      status(gender) mustBe OK
      contentType(gender) mustBe Some("text/html")
      contentAsString(gender) must include ("gender")
    }
    "render the genderForm page from the application" in {
      val controller = inject[FormController]
      val gender = controller.genderForm().apply(FakeRequest(GET, "/gender"))
      status(gender) mustBe OK
      contentType(gender) mustBe Some("text/html")
      contentAsString(gender) must include ("gender")
    }




    //job
    "render the jobForm() from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val job = controller.jobForm().apply(FakeRequest(GET, "/job"))
      status(job) mustBe OK
      contentType(job) mustBe Some("text/html")
      contentAsString(job) must include ("job")
    }
    "render the jobForm page from the application" in {
      val controller = inject[FormController]
      val job = controller.jobForm().apply(FakeRequest(GET, "/job"))
      status(job) mustBe OK
      contentType(job) mustBe Some("text/html")
      contentAsString(job) must include ("job")
    }




    //colour
    "render the colourForm() from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val colour = controller.colourForm().apply(FakeRequest(GET, "/colour"))
      status(colour) mustBe OK
      contentType(colour) mustBe Some("text/html")
      contentAsString(colour) must include ("colour")
    }
    "render the colourForm page from the application" in {
      val controller = inject[FormController]
      val colour = controller.colourForm().apply(FakeRequest(GET, "/colour"))
      status(colour) mustBe OK
      contentType(colour) mustBe Some("text/html")
      contentAsString(colour) must include ("colour")
    }

  }

  "FormController submitNameForm()  " should {

    "the json body is valid " when {
      val jsonBody: JsObject = Json.obj (
        "name" -> "John"
      )

      "return redirect/see_other " in {

        val result = testFormController.submitNameForm().apply(FakeRequest(POST, "/name").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER


      }

    }

    "the json body is invalid " when {

      "return BadRequest " in {

        val result = testFormController.submitAgeForm().apply(FakeRequest(POST, "/name"))
        status(result) mustBe Status.BAD_REQUEST


      }

    }

  }

  "FormController submitAgeForm()  " should {

    "the json body is valid " when {
      val jsonBody: JsObject = Json.obj (
        "age" -> 23
      )

      "return redirect/see_other " in {

        val result = testFormController.submitAgeForm().apply(FakeRequest(POST, "/age").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER


      }

    }

    "the json body is invalid " when {
      val jsonBody: JsObject = Json.obj (
        "age" -> "hfdshfh"
      )

      "return BadRequest " in {

        val result = testFormController.submitAgeForm().apply(FakeRequest(POST, "/age").withJsonBody(jsonBody))
        status(result) mustBe Status.BAD_REQUEST


      }

    }

  }

  "FormController submitGenderForm()  " should {

    "the json body is valid " when {
      val jsonBody: JsObject = Json.obj (
        "gender" -> "Male"
      )

      "return redirect/see_other " in {

        val result = testFormController.submitGenderForm().apply(FakeRequest(POST, "/gender").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER


      }

    }

    "the json body is invalid " when {

      "return BadRequest " in {

        val result = testFormController.submitGenderForm().apply(FakeRequest(POST, "/gender"))
        status(result) mustBe Status.BAD_REQUEST


      }

    }

  }

  "FormController submitJobForm()  " should {

    "the json body is valid " when {
      val jsonBody: JsObject = Json.obj (
        "job" -> "Developer"
      )

      "return redirect/see_other " in {

        val result = testFormController.submitJobForm().apply(FakeRequest(POST, "/job").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER


      }

    }

    "the json body is invalid " when {

      "return BadRequest " in {

        val result = testFormController.submitJobForm().apply(FakeRequest(POST, "/job"))
        status(result) mustBe Status.BAD_REQUEST


      }

    }

  }

  "FormController submitColourForm()  " should {

    "the json body is valid " when {
      val jsonBody: JsObject = Json.obj (
        "colour" -> List("red", "blue")
      )

      "return redirect/see_other " in {

        val result = testFormController.submitColourForm().apply(FakeRequest(POST, "/colour").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER


      }

    }
//
//    "the json body is invalid " when {
//
//      "return BadRequest " in {
//
//        val result = testFormController.submitColourForm().apply(FakeRequest(POST, "/colour"))
//        status(result) mustBe Status.BAD_REQUEST
//
//
//      }
//
//    }
//
   }


}
