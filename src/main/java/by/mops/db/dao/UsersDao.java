package by.mops.db.dao;

import by.mops.models.User;
import java.util.List;

public interface UsersDao extends CrudDao<User>{
    List<User> findAllByFirstName(String firstName);
}
