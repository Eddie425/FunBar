package tw.FunBar.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LikePost")
public class LikePost implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LikePK likePK;

	public LikePK getLikePK() {
		return likePK;
	}

	public void setLikePK(LikePK likePK) {
		this.likePK = likePK;
	}
	
	

}
