package controllers.security;

import play.mvc.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;

import models.users.*;

public class AuthAdmin extends Action.Simple {
    
    public CompletionStage<Result> call(Http.Context ctx){

        String id = ctx.session().get("email");

        if(id != null){
            Users u = Users.getUserById(id);
            if("admin".equals(u.getNumber())) {
                
                return delegate.call(ctx);
            }
        }
        ctx.flash().put("error","Admin Login Required.");
        return CompletableFuture.completedFuture(redirect(controllers.security.routes.LoginController.login()));
    }
}