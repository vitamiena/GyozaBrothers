package Material;

public class StructuresChangedException extends StructuresException {
  public StructuresChangedException() {
    super("クラスStructureは位置・サイズを変更できません");
  }
}