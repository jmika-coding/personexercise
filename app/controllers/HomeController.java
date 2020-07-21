package controllers;

import connection.DatabaseConnection;
import model.PersonEntity;
import model.PersonModel;
import model.PersonRepository;
import org.bson.Document;
import play.api.i18n.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    MessagesApi messagesApi;

    PersonRepository personRepository;
    DatabaseConnection databaseConnection;

    @Inject
    public HomeController(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        this.personRepository = new PersonRepository(databaseConnection);
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result helloAll() {
        List<PersonEntity> responsePersonList = personRepository.fetchAllPerson();
        return ok(views.html.helloAll.render(responsePersonList));
    }

    public Result helloOne(String id) {
        List<PersonEntity> responsePersonList = personRepository.fetchOnePerson(id);
        return ok(views.html.hello.render(responsePersonList));
    }

    // To insert a person into database, get fields values from form
    public Result personForm(Http.Request request) {
        MessagesProvider m = () -> this.messagesApi.preferred(request);
        Form<PersonModel> a = formFactory.form(PersonModel.class).bindFromRequest(request);
        return ok(views.html.form.render(a, m));
    }

    public Result personFormSubmit(Http.Request request) {
        Form<PersonModel> a = formFactory.form(PersonModel.class).bindFromRequest(request);
        PersonModel personModel = a.withDirectFieldAccess(true).get();

        Document document = new Document()
                .append("name", personModel.getName())
                .append("firstName", personModel.getFirstName())
                .append("age", personModel.getAge());

        personRepository.saveCollection(document);
        return redirect(controllers.routes.HomeController.index());
    }

    public Result delete(String id) {
        System.out.println("delete");
        System.out.println(id);
        personRepository.delete(id);
        return redirect(controllers.routes.HomeController.helloAll());
    }

    public Result update(String id) {
        List<PersonEntity> responsePersonList = personRepository.fetchOnePerson(id);
        PersonEntity personEntity = responsePersonList.get(0);
        String url = controllers.routes.HomeController.personForm() + "?name=" + personEntity.getName() + "&firstName=" + personEntity.getFirstName() + "&age=" + personEntity.getAge();
        return redirect(url);
    }

}
