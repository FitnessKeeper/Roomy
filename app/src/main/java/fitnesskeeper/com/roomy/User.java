package fitnesskeeper.com.roomy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * @author stuckj, created on 6/29/17.
 */

@Entity(tableName = "user", indices = {@Index("last_name"), @Index(value = "uuid", unique = true)})
public class User
{
    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String uuid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "created")
    @TypeConverters(DateConverters.class)
    private Date creationDate;

    public int getFoo()
    {
        return foo;
    }

    public void setFoo(int foo)
    {
        this.foo = foo;
    }

    private int foo;

    @Embedded
    private MyLocation lastLocation;

    public User(String uuid, String firstName, String lastName, String email, Date creationDate, MyLocation lastLocation)
    {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.creationDate = creationDate;
        this.lastLocation = lastLocation;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public MyLocation getLastLocation()
    {
        return lastLocation;
    }

    public void setLastLocation(MyLocation lastLocation)
    {
        this.lastLocation = lastLocation;
    }
}