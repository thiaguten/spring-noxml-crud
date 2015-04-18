package br.com.thiaguten.spring.model.base;

public abstract class AbstractModel implements Persistable<Long>, Versionable {

    private static final long serialVersionUID = 1215051259136146694L;

    public boolean hasID() {
        return getId() != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractModel other = (AbstractModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }

}
