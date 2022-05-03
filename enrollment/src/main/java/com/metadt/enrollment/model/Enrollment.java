package com.metadt.enrollment.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name="Enrollment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ENROLLMENT")
public class Enrollment implements Serializable {
  
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",  unique=true, updatable = false, nullable=false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    private Course course;
    
    @Column(name="SECTION", nullable=false, length=2)
    private String section;
    
    @Column(name="ROOM", nullable=false, length=8)
    private String room;
    
    @Column(name="SEMESTER", nullable=false, length=5)
    private String semester;
    
    @Column(name="GRADE", nullable=false, scale=3)
    private Integer grade;
 
    /** Primary key. */
    protected static final String PK = "id";
    
   	public static String getPk() {
   		return PK;
   	}

    /**
     * Compares the key for this instance with another Course.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Course and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Enrollment)) {
            return false;
        }
        Enrollment that = (Enrollment) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Course.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Enrollment)) return false;
        return this.equalKeys(other) && ((Enrollment)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 23;
        if (getId() == null) {
            i = 0;
        } else {
            i = getId().hashCode();
        }
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[ Enrollment | ");
        sb.append("id=").append(getId()).append(" | ");
        sb.append("studentId=").append(getStudent().getId()).append(" | ");
        sb.append("courseId=").append(getCourse().getId()).append(" ]");
        return sb.toString();
    }
    
    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    public String toStringFull() {
    	
        StringBuffer sb = new StringBuffer("[ Enrollment | ");
        sb.append(getId()).append(" | ");
        sb.append(getStudent().getId()).append(" | ");
        sb.append(getCourse().getId()).append(" | ");
        sb.append(getSection()).append(" | ");
        sb.append(getRoom()).append(" | ");
        sb.append(getSemester()).append(" | ");
        sb.append(getGrade()).append(" ]");;
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(1);
        ret.put("Id", getId());
        return ret;
    }

}