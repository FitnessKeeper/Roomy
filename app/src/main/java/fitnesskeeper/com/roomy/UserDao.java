package fitnesskeeper.com.roomy;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * @author stuckj, created on 6/29/17.
 */

@Dao
public interface UserDao
{
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(final List<Integer> userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND"
            + ":last LIKE :last LIMIT 1")
    User findByName(final String first, final String last);

    @Query("SELECT * FROM user WHERE last_name LIKE :last LIMIT 1")
    User findByLastName(final String last);

    @Query("SELECT first_name, last_name FROM user WHERE uid = :id LIMIT 1")
    UserName getNameById(final int id);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT uid, email FROM user WHERE uid = :id LIMIT 1")
    User getLazyEmailById(final int id);

    @Query("SELECT email FROM user WHERE uid = :id LIMIT 1")
    String getEmailById(final int id);

    @Query("SELECT * FROM user WHERE uid = :userId")
    LiveData<User> findById(final Integer userId);

    @Query("SELECT * FROM user ORDER BY created DESC LIMIT 1")
    LiveData<User> getLastAddedUser();

    @Insert
    void insertAll(final User... users);

    @Update(onConflict = REPLACE)
    void update(final User user);

    @Delete
    void delete(final User user);

    @Delete
    void deleteAll(final User... users);

    @Query("DELETE FROM user")
    void deleteAllUsers();
}
