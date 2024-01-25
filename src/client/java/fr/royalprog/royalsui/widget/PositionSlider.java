package fr.royalprog.royalsui.widget;

import fr.royalprog.royalsui.config.ModConfigs;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.realms.gui.screen.RealmsSlotOptionsScreen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class PositionSlider extends SliderWidget {
  private final int min;
  private final int max;
  private int xvalue;
  private String text;

  public PositionSlider(int x, int y, int width, int value, int min, int max,
                        String text) {
    super(x, y, width, 20, ScreenTexts.EMPTY, value);
    this.min = min;
    this.max = max;
    this.value =
        ((double)(MathHelper.clamp(value, min, max) - min) / (max - min));
    this.xvalue = value;
    this.text = text;
    this.updateMessage();
  }

  public void applyValue() {
    xvalue = (int)MathHelper.lerp(MathHelper.clamp(this.value, 0, 1), this.min,
                                  this.max);
  }

  protected void updateMessage() {
    this.setMessage(ScreenTexts.composeGenericOptionText(
        Text.literal(text), Text.literal(String.valueOf(xvalue))));
  }

  public int getValue() { return this.xvalue; }
}
