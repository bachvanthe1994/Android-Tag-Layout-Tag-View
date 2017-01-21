# Android-Tag-Layout-Tag-View
Tag Layout, Tag View

+1 star if you like :)

[![Material Design - SupperMarkets](http://img.youtube.com/vi/QXWSwX4kNd4/0.jpg)](https://www.youtube.com/watch?v=QXWSwX4kNd4)

```java
	        // attach to current activity;
	        resideMenu = new ResideMenu(this);
	        resideMenu.setBackground(R.drawable.menu_background);
	        resideMenu.attachToActivity(this);
	
	        // create menu items;
	        String titles[] = { "Home", "Profile", "Calendar", "Settings" };
	        int icon[] = { R.drawable.icon_home, R.drawable.icon_profile, R.drawable.icon_calendar, R.drawable.icon_settings };
	
	        for (int i = 0; i < titles.length; i++){
	            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
	            item.setOnClickListener(this);
	            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
	        }
```
