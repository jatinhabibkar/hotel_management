<!doctype html>
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>database connections</title>
    </head>
    <body>
      <?php
      $username = "root";
      $password = "";
      $host = "localhost";
      $dbname = "hotel";

      $connector = new mysqli($host,$username,$password,$dbname)
          or die("Unable to connect");
        echo "Connections are made successfully::";
    //   $selected = mysql_select_db("test_db", $connector)
    //     or die("Unable to connect");

      //execute the SQL query and return records      
      $result = mysqli_query($connector,"SELECT * FROM `allorder` WHERE 1 ")or die("Error: " . mysqli_error($connector));
      ?>
      <table border="2" style= "background-color: #84ed86; c    olor: #761a9b; margin: 0 auto;" >
      <thead>
        <tr>
          <th>orderid</th>
          <th>list of dishes</th>
          <th>Totalprice</th>
        </tr>
      </thead>
      <tbody>
        <?php
        $row = $result->fetch_array();
        echo "$row[orderid]";
        ?>

<?php echo "$row[orderid]"; ?>
<?php echo "$row[listOfdishes]"; ?>
<?php echo "$row[TotalPrice]"; ?>


<?php
while($table=mysqli_fetch_array($row)){
    echo "$table[orderid]";
    echo "$table[listOfdishes]";
    echo "$table[TotalPrice]";
}

?>
      </tbody>
    </table>
     <?php mysql_close($connector); ?>
    </body>
    </html>