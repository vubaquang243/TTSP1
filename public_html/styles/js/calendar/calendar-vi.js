// ** I18N

// Calendar EN language
// Author: Mihai Bazon, <mihai_bazon@yahoo.com>
// Encoding: any
// Distributed under the same terms as the calendar itself.

// For translators: please use UTF-8 if possible.  We strongly believe that
// Unicode is the answer to a real internationalized world.  Also please
// include your contact information in the header, as can be seen above.

// full day names
Calendar._DN = new Array
("Ch&#7911; nh&#7853;t",
 "Th&#7913; hai",
 "Th&#7913; ba",
 "Th&#7913; t&#432;",
 "Th&#7913; n&#259;m",
 "Th&#7913; s&aacute;u",
 "Th&#7913; b&#7843;y",
 "Ch&#7911; nh&#7853;t");

// Please note that the following array of short day names (and the same goes
// for short month names, _SMN) isn't absolutely necessary.  We give it here
// for exemplification on how one can customize the short day names, but if
// they are simply the first N letters of the full name you can simply say:
//
//   Calendar._SDN_len = N; // short day name length
//   Calendar._SMN_len = N; // short month name length
//
// If N = 3 then this is not needed either since we assume a value of 3 if not
// present, to be compatible with translation files that were written before
// this feature.

// short day names
Calendar._SDN = new Array
("Ch&#7911; nh&#7853;t",
 "Th&#7913; hai",
 "Th&#7913; ba",
 "Th&#7913; t&#432;",
 "Th&#7913; n&#259;m",
 "Th&#7913; s&aacute;u",
 "Th&#7913; b&#7843;y",
 "Ch&#7911; nh&#7853;t");

// First day of the week. "0" means display Sunday first, "1" means display
// Monday first, etc.
Calendar._FD = 0;

// full month names
Calendar._MN = new Array
("Th&aacute;ng 1",
 "Th&aacute;ng 2",
 "Th&aacute;ng 3",
 "Th&aacute;ng 4",
 "Th&aacute;ng 5",
 "Th&aacute;ng 6",
 "Th&aacute;ng 7",
 "Th&aacute;ng 8",
 "Th&aacute;ng 9",
 "Th&aacute;ng 10",
 "Th&aacute;ng 11",
 "Th&aacute;ng 12");

// short month names
Calendar._SMN = new Array
("Th&aacute;ng 1",
 "Th&aacute;ng 2",
 "Th&aacute;ng 3",
 "Th&aacute;ng 4",
 "Th&aacute;ng 5",
 "Th&aacute;ng 6",
 "Th&aacute;ng 7",
 "Th&aacute;ng 8",
 "Th&aacute;ng 9",
 "Th&aacute;ng 10",
 "Th&aacute;ng 11",
 "Th&aacute;ng 12");

// tooltips
Calendar._TT = {};
Calendar._TT["INFO"] = "Th&ocirc;ng tin v&#7873; l&#7883;ch ";

Calendar._TT["ABOUT"] =
"Calendar Input";
Calendar._TT["ABOUT_TIME"] = "\n\n" +
"Time selection:\n" +
"- Click on any of the time parts to increase it\n" +
"- or Shift-click to decrease it\n" +
"- or click and drag for faster selection.";

Calendar._TT["PREV_YEAR"] = "N&#259;m tr&#432;&#7899;c";
Calendar._TT["PREV_MONTH"] = "Th&aacute;ng tr&#432;&#7899;c";
Calendar._TT["GO_TODAY"] = "Ch&#7885;n ng&agrave;y h&ocirc;m nay";
Calendar._TT["NEXT_MONTH"] = "Th&aacute;ng ti&#7871;p theo";
Calendar._TT["NEXT_YEAR"] = "N&#259;m ti&#7871;p theo";
Calendar._TT["SEL_DATE"] = "Ch&#7885;n ng&agrave;y";
Calendar._TT["DRAG_TO_MOVE"] = "Gi&#7919; chu&#7897;t &#273;&#7875; di chuy&#7875;n";
Calendar._TT["PART_TODAY"] = " (H&ocirc;m nay)";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT["DAY_FIRST"] = "Ch&#7885;n %s l&agrave; ng&agrave;y &#273;&#7847;u ti&ecirc;n ";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT["WEEKEND"] = "0,6";

Calendar._TT["CLOSE"] = "&#272;&oacute;ng";
Calendar._TT["TODAY"] = "H&ocirc;m nay";
Calendar._TT["TIME_PART"] = "(Shift-)Click or drag to change value";

// date formats
Calendar._TT["DEF_DATE_FORMAT"] = "%Y-%m-%d";
Calendar._TT["TT_DATE_FORMAT"] = "%a, %b ng&agrave;y %e";

Calendar._TT["WK"] = "Tu&#7847;n";
Calendar._TT["TIME"] = "Time:";
