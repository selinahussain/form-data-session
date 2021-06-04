package controllers

import play.api.data.Form
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request, Results}
import play.api.data.Forms.{boolean, mapping, number, text}

import javax.inject.Inject



class FormController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def nameForm() = Action{ implicit request: Request[AnyContent] =>

//    val receivedData = request.body.asFormUrlEncoded
//
//    val nameFromInput = receivedData.map{
//      args =>
//        println("args " + args)
//      NameForm.form.fill(NameFormModel(args("name").head))
//
//    }.getOrElse(NameForm.form)
//
//    println("nameform " + nameFromInput)
    Ok(views.html.name(NameForm.form.fill(NameFormModel(""))))


  }

  def submitNameForm() = Action { implicit request =>

    NameForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.name(formWithErrors)),
      success => Redirect(controllers.routes.FormController.ageForm())
        .withSession(request.session + ("name" -> s"${success.name}"))
    )

  }


  def ageForm() = Action{ implicit request: Request[AnyContent] =>

    val receivedData = request.body.asFormUrlEncoded

    val ageFromInput = receivedData.map{
      args =>
      AgeForm.form.fill(AgeFormModel(args("age").head.toInt))
    }.getOrElse(AgeForm.form)

    println("agefomr " + request.session)
    Ok(views.html.age(ageFromInput))

  }

  def submitAgeForm() = Action { implicit request =>

    AgeForm.form
      .bindFromRequest()
      .fold(
      formWithErrors => BadRequest(views.html.age(formWithErrors)),
      success => Redirect(controllers.routes.FormController.genderForm())
        .withSession(request.session + ("age" -> s"${success.age}" ))
    )

  }


  def genderForm() = Action { implicit request: Request[AnyContent] =>


    val receivedData = request.body.asFormUrlEncoded


    val genderFromInput = receivedData.map{
      args =>
        GenderForm.form.fill(GenderFormModel(args("gender").head))
    }.getOrElse(GenderForm.form)

    println("genderform " + request.session)

    Ok(views.html.gender(genderFromInput))

  }

  def submitGenderForm() = Action { implicit request =>

    GenderForm.form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.gender(formWithErrors)),
        success => Redirect(controllers.routes.FormController.jobForm())
          .withSession(request.session + ("gender" -> s"${success.gender}" ))
      )

  }




  def jobForm() = Action { implicit request: Request[AnyContent] =>

    val receivedData = request.body.asFormUrlEncoded

    val jobFromInput = receivedData.map{
      args =>
        JobForm.form.fill(JobFormModel(args("job").head))

    }.getOrElse(JobForm.form)

    println("jobform " + request.session)

    Ok(views.html.job(jobFromInput))

  }

  def submitJobForm() = Action { implicit request: Request[AnyContent] =>

    JobForm.form
      .bindFromRequest()
      .fold(
      formWithErrors => BadRequest(views.html.job(formWithErrors)),
      success => Redirect(controllers.routes.FormController.colourForm())
        .withSession(request.session + ("job" -> s"${success.job}" ))
    )

  }




  def colourForm() =Action{ implicit request: Request[AnyContent] =>
    val receivedData = request.body.asFormUrlEncoded

    val coloursFromInput = receivedData.map{args =>
      args.keys
    }.getOrElse(Ok("Error"))
    println("colour " + request.session)

    Ok(views.html.colour(ColourForm.form)).withSession(request.session + ("colour" -> s"${coloursFromInput}"))

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

case class GenderFormModel(gender: String)
object GenderForm {
  val form : Form[GenderFormModel] = Form(
    mapping(
      "gender" -> text

    )(GenderFormModel.apply)(GenderFormModel.unapply)
  )

}

case class JobFormModel(job: String)
object JobForm {

  val form : Form[JobFormModel] = Form(
    mapping(
      "job" -> text

    )(JobFormModel.apply)(JobFormModel.unapply)
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

