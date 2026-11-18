# Resflix 2.0.0

Native Android home-screen widget collection with a monochrome / Nothing-inspired visual style.

## Included widgets
- Digital clock (Android TextClock for continuously updated time)
- Dot-style digital clock
- Analog clock
- Date card
- Calendar date card
- Battery percentage card
- Wide battery meter
- Music app shortcut
- Weather placeholder card

## Build
Build with `gradle :app:assembleDebug`.

## Notes
Add widgets from the Android launcher's widget picker.
Music card opens an installed music app; it does not read track metadata or provide transport controls.
Weather is still a placeholder in this release; no live forecast or location tracking is claimed.
Battery widgets refresh when the widget is updated or Resflix refresh is used.
