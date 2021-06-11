package controllers

import play.api.data.Form
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import play.api.data.Forms.{list, mapping, number, text}

import javax.inject.Inject



class FormController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def nameForm() = Action{ implicit request: Request[AnyContent] =>

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

    Ok(views.html.age(AgeForm.form.fill(AgeFormModel())))

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

    Ok(views.html.gender(GenderForm.form.fill(GenderFormModel(""))))

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

    Ok(views.html.job(JobForm.form.fill(JobFormModel(""))))

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




  def colourForm() = Action { implicit request: Request[AnyContent] =>

    Ok(views.html.colour(ColourForm.form.fill(ColourFormModel(List()))))

  }


  def submitColourForm() = Action { implicit request: Request[AnyContent] =>

    ColourForm.form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.colour(formWithErrors)),
        success => Redirect(controllers.routes.HomeController.summary())
          .withSession(request.session + ("colour" -> s"${success.colour}" ))
      )

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

case class AgeFormModel(age: Int = 0)
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

case class ColourFormModel(colour: List[String])
object ColourForm {
  val form : Form[ColourFormModel] = Form(
    mapping(
      "colour" -> list(text)

    )(ColourFormModel.apply)(ColourFormModel.unapply)
  )

}

