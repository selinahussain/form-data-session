package controllers

import play.api.data.Form
import play.api.mvc.{AbstractController, AnyContent, BaseController, ControllerComponents, Request, Results}
import play.api.data.Forms.{boolean, mapping, number, text}

import javax.inject.Inject



class FormController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def nameForm() = Action{ implicit request: Request[AnyContent] =>
    val receivedData = request.body.asFormUrlEncoded

    val nameFromInput = receivedData.map{args =>
      NameForm.form.fill(NameFormModel(args("Name").head))
    }.getOrElse(NameForm.form)

    Ok(views.html.name(nameFromInput))

  }

  def submitNameForm() = Action { implicit request =>

    NameForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.name(formWithErrors)),
      success => Redirect(controllers.routes.FormController.ageForm()).withSession(request.session + ("name" -> s"$success.name}"))
    )
  }


  def ageForm() = Action{ implicit request: Request[AnyContent] =>

    val receivedData = request.body.asFormUrlEncoded

    val ageFromInput = receivedData.map{args =>
      AgeForm.form.fill(AgeFormModel(args("age").head.toInt))
    }.getOrElse(AgeForm.form)


    Ok(views.html.age(ageFromInput))

  }

  def submitAgeForm() = Action { implicit request =>

    AgeForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.age(formWithErrors)),
      success => Redirect(controllers.routes.FormController.genderForm()).withSession(request.session + ("age" -> s"${success.age}" ))
    )
  }


  def genderForm() =Action{ implicit request: Request[AnyContent] =>
    val receivedData = request.body.asFormUrlEncoded
    println("gender " + receivedData)

    val genderFromInput = receivedData.map{args =>
      args("gender").head
    }.getOrElse(Ok("Error"))
    Ok(views.html.gender(GenderForm.form)).withSession(request.session + ("gender" -> s"$genderFromInput"))

  }

  def jobForm() = Action { implicit request: Request[AnyContent] =>

    val receivedData = request.body.asFormUrlEncoded

    val jobFromInput = receivedData.map{args =>
      args("job").head
    }.getOrElse(Ok("Error"))

    Ok(views.html.job(JobForm.form)).withSession(request.session + ("job" -> s"$jobFromInput"))


  }

  def colourForm() =Action{ implicit request: Request[AnyContent] =>
    val receivedData = request.body.asFormUrlEncoded

    val coloursFromInput = receivedData.map{args =>
      args.keys
    }.getOrElse(Ok("Error"))

    Ok(views.html.colour(ColourForm.form)).withSession("colour" -> s"${coloursFromInput}")

  }

}



case class NameFormModel(name: String)
object NameForm {
  val form : Form[NameFormModel] = Form(
    mapping(
      "name" -> text

    )(NameFormModel.apply)(NameFormModel.unapply)
  )

}

case class AgeFormModel(age: Int)
object AgeForm {
  val form : Form[AgeFormModel] = Form(
    mapping(
      "age" -> number

    )(AgeFormModel.apply)(AgeFormModel.unapply)
  )

}

case class GenderForm(woman: Boolean, man: Boolean)
object GenderForm {
  val form : Form[GenderForm] = Form(
    mapping(
      "woman" -> boolean,
      "man" -> boolean

    )(GenderForm.apply)(GenderForm.unapply)
  )

}

case class JobForm(job: String)
object JobForm {

  val form : Form[JobForm] = Form(
    mapping(
      "job" -> text

    )(JobForm.apply)(JobForm.unapply)
  )

}

case class ColourForm(blue: Boolean, purple: Boolean, green: Boolean, pink: Boolean, red: Boolean )
object ColourForm {
  val form : Form[ColourForm] = Form(
    mapping(
      "blue" -> boolean,
      "purple" -> boolean,
      "green" -> boolean,
      "pink" -> boolean,
      "red" -> boolean

    )(ColourForm.apply)(ColourForm.unapply)
  )

}

