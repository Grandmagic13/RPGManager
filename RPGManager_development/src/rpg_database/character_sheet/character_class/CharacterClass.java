package rpg_database.character_sheet.character_class;

public class CharacterClass {

	private BaseClasses baseClass;
	private SpecializationClasses specializationClass;

	public CharacterClass(BaseClasses baseClass, SpecializationClasses specializationClass) {
		if (isSpecializationCompatibleWithBaseClass(baseClass, specializationClass)) {
			this.baseClass = baseClass;
			this.specializationClass = specializationClass;
		} else {
			throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", baseClass,
					specializationClass));
		}
	}

	private boolean isSpecializationCompatibleWithBaseClass(BaseClasses baseClass,
			SpecializationClasses specializationClass) {
		return (specializationClass.hasBase() && specializationClass.getBaseClass().equals(baseClass))
				|| !specializationClass.hasBase();
	}

	public BaseClasses getBaseClass() {
		return baseClass;
	}

	public SpecializationClasses getSpecializationClass() {
		return specializationClass;
	}

	public void setBaseClass(BaseClasses baseClass) {
		if (isSpecializationCompatibleWithBaseClass(baseClass, this.specializationClass)) {
			this.baseClass = baseClass;
		} else {
			throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", baseClass,
					specializationClass));
		}
	}

	public void setSpecializationClass(SpecializationClasses specializationClass) {
		if (isSpecializationCompatibleWithBaseClass(this.baseClass, specializationClass)) {
			this.specializationClass = specializationClass;
		} else {
			throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", baseClass,
					specializationClass));
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!CharacterClass.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final CharacterClass other = (CharacterClass) obj;
		if ((this.baseClass == null) ? (other.getBaseClass() != null) : !this.baseClass.equals(other.getBaseClass())) {
			return false;
		} else if ((this.specializationClass == null) ? (other.getSpecializationClass() != null)
				: !this.specializationClass.equals(other.getSpecializationClass())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("BaseClass: %s, SpecializationClass: %s", this.baseClass.toString(),
				this.specializationClass.toString());
	}

}
